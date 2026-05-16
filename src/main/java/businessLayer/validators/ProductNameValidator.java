package businessLayer.validators;

import Model.Product;

public class ProductNameValidator implements Validator<Product>{
    public void validate(Product p) {
        if(p.getName().length()==0)
            throw new IllegalArgumentException("Product name cannot be empty");
    }
}
