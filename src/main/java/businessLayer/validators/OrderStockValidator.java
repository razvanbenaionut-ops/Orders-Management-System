package businessLayer.validators;

import Model.Orders;
import Model.Product;
import dataAccessLayer.ProductDAO;

import java.util.NoSuchElementException;

public class OrderStockValidator implements Validator<Orders>{
    private ProductDAO productDAO;
    public OrderStockValidator(ProductDAO productDAO)
    {
        this.productDAO=productDAO;
    }

    public void validate(Orders order)
    {
        Product product=productDAO.findById(order.getProduct_id());
        if(product==null)
        {
            throw new NoSuchElementException("Product not found");
        }
        if(product.getStock()<order.getQuantity())
        {
            throw new IllegalArgumentException("Not enough stock");
        }
    }
}
