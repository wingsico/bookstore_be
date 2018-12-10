package org.wingsico.bookstore.validator;

import org.wingsico.bookstore.annotation.PaymentInformation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PaymentInformationValidator implements ConstraintValidator<PaymentInformation, String> {
    /**
     * 校验器初始化
     * @param paymentInformation
     */
    @Override
    public void initialize(PaymentInformation paymentInformation){ }

    /**
     *
     * @param payment
     * @param constraintValidator
     * @return
     */
    @Override
    public boolean isValid(String payment, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Pattern pattern = Pattern.compile("\\d{6}");
            return pattern.matcher(payment).matches();
        } catch (NullPointerException ex) {
        }
        return true;
    }
}
