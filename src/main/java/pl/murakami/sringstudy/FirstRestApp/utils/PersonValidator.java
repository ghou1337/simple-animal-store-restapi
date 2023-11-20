package pl.murakami.sringstudy.FirstRestApp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.murakami.sringstudy.FirstRestApp.model.User;
import pl.murakami.sringstudy.FirstRestApp.service.UserService;

@Component
public class PersonValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) throws RuntimeException {
        User user = (User) target;
        if (userService.checkIfUsernameExist(user.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "User already exist");
        }
    }
}
