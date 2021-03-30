package businessLogic.validators;

import model.User;

public class UsernameValidator implements Validator<User>{

    @Override
    public void validate(User object) {
        if(!object.getUsername().matches("^[A-Za-z0-9]+")) {
            throw new IllegalArgumentException("Username is not a valid name!");
        }
    }
}
