package org.wingsico.bookstore.validator;

import org.wingsico.bookstore.annotation.PasswordInformation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordInformationValidator implements ConstraintValidator<PasswordInformation, String> {
    /**
     * 校验器初始化
     * @param passwordInformation
     */
    @Override
    public void initialize(PasswordInformation passwordInformation){ }

    /**
     *
     * @param password
     * @param constraintValidator
     * @return
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
        return pattern.matcher(password).matches();
    }
}
