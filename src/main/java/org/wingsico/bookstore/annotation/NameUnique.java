package org.wingsico.bookstore.annotation;

import org.wingsico.bookstore.validator.NameUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，以保证用户名唯一
 *
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NameUniqueValidator.class)
public @interface NameUnique {
    String message() default "用户名重复";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
