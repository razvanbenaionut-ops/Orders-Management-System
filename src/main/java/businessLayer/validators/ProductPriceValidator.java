package businessLayer.validators;

import Model.Product;

public class ProductPriceValidator implements Validator<Product>{
    public void validate(Product p) {
        if(p.getPrice()<=0)
            throw new IllegalArgumentException("Product price cannot be negative");
    }
}
