package businessLogic.validators;

import model.Product;

public class PriceValidator implements Validator<Product> {
    @Override
    public void validate(Product object) {
        if(!Double.toString(object.getPrice()).matches("[0-9]*\\.[0-9]{2}")) {
            throw new IllegalArgumentException("Product price is not a valid name!");
        }
    }
}
