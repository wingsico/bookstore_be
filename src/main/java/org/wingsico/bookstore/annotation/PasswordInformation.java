package org.wingsico.bookstore.annotation;

import org.wingsico.bookstore.validator.PasswordInformationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 确保密码信息符合规范
 *
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordInformationValidator.class)
public @interface PasswordInformation {
    String message() default "密码必须由6-16位的英文和数字组成";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
