package org.wingsico.bookstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.wingsico.bookstore.annotation.NameUnique;
import org.wingsico.bookstore.domain.User;
import org.wingsico.bookstore.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * 实现用户名唯一的校验器
 *
 */
public class NameUniqueValidator implements ConstraintValidator<NameUnique, String>{
    @Autowired
    private UserService userService;
    /**
     * 校验器初始化
     * @param nameUnique
     */
    @Override
    public void initialize(NameUnique nameUnique) { }

    /**
     * 校验方法
     * @param userName
     * @param constraintValidatorContext
     */
    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        try{
            List<User> users = userService.findAll();
            for (User user:users) {
                if (userName.equals(user.getUserName())) {
                    return false;
                }
            }
            return true;
        }catch (NullPointerException ex){

        }
        return true;
    }
}
