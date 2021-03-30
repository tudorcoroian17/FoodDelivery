package businessLogic.validators;

import model.Product;

public class ProductNameValidator implements Validator<Product> {

    @Override
    public void validate(Product object) {
        if(!object.getName().matches("^[A-Za-z ]+$")) {
            throw new IllegalArgumentException("Product name is not a valid name!");
        }
    }
}
