package businessLogic.validators;

import model.User;

public class PasswordValidator implements Validator<User>{

    @Override
    public void validate(User object) {
        if(!object.getPassword().matches("^[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Password is not a valid name!");
        }
    }
}
