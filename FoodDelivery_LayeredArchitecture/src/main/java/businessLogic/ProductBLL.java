package businessLogic;

import access.ProductAccess;
import businessLogic.validators.PriceValidator;
import businessLogic.validators.ProductNameValidator;
import businessLogic.validators.Validator;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductAccess productAccess;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new PriceValidator());
        validators.add(new ProductNameValidator());
        productAccess = new ProductAccess();
    }

    public List<Product> findProductByName(String name) {
        return productAccess.findProductByName(name);
    }

    public Product findProductById(int id) {
        Product product = productAccess.findProductById(id).get(0);
        return product;
    }

    public List<Product> findAllProducts() {
        return productAccess.findAllProducts();
    }

    public void insertProduct(Product product) {
        for (Validator<Product> validator : validators) {
            validator.validate(product);
        }
        productAccess.insert(product);
    }

    public void update(Product oldProduct, Product product) {
        for (Validator<Product> validator : validators) {
            validator.validate(product);
        }
        //Product productOld = findProductById(product.getProduct_id());
        productAccess.update(oldProduct, product);
    }

    public void deleteProduct(Product product) {
        productAccess.delete(product);
    }

    public void deleteProductById(int id) {
        productAccess.deleteById(id);
    }

    //Maybe cascade the deletes and updates??

}
