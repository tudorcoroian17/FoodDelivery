package businessLogic.validators;

import model.OrderItem;

public class QuantityValidator implements Validator<OrderItem> {

    @Override
    public void validate(OrderItem object) {
        if(!Integer.toString(object.getQuantity()).matches("[0-9]*")) {
            throw new IllegalArgumentException("Client name is not a valid name!");
        }
    }
}
