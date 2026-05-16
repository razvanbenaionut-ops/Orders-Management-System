package businessLayer.validators;

import Model.Product;

public class ProductStockValidator implements Validator<Product>{
    public void validate(Product p) {
        if(p.getStock()<0)
            throw new IllegalArgumentException("Product stock cannot be negative");
    }
}
