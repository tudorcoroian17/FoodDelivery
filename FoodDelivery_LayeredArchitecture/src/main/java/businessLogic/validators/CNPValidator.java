package businessLogic.validators;

import model.User;

public class CNPValidator implements Validator<User>{

    @Override
    public void validate(User object) {
        if(!object.getCnp().matches("[1256][0-9]{2}[01][0-9][0123][0-9]{7}")) {
            throw new IllegalArgumentException("Client CNP is not a valid name!");
        }
    }
}
