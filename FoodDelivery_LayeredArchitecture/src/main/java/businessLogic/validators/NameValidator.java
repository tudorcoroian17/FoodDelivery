package businessLogic.validators;

import model.User;

public class NameValidator implements Validator<User>{

    @Override
    public void validate(User object) {
        if(!object.getName().matches("^[A-Za-z ]+$")) {
            throw new IllegalArgumentException("Client/Admin name is not a valid name!");
        }
    }
}
