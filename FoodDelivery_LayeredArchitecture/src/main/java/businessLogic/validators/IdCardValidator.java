package businessLogic.validators;

import model.User;

public class IdCardValidator implements Validator<User>{

    @Override
    public void validate(User object) {
        if(!object.getId_card().matches("CJ[0-9]{6}|KX[0-9]{6}")) {
            throw new IllegalArgumentException("Client ID card is not a valid name!");
        }
    }
}
