package org.wingsico.bookstore.annotation;

import org.wingsico.bookstore.validator.PaymentInformationValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 确保支付密码符合规范
 *
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PaymentInformationValidator.class)
public @interface PaymentInformation {
    String message() default "支付密码必须由6位数字组成";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default { };
}
