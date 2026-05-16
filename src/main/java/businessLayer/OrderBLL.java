package businessLayer;

import Model.Client;
import Model.Orders;
import Model.Product;
import businessLayer.validators.OrderQuantityValidator;
import businessLayer.validators.OrderStockValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.ClientDAO;
import dataAccessLayer.OrderDAO;
import dataAccessLayer.ProductDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;
    private ProductDAO productDAO;
    private ClientDAO clientDAO;
    public OrderBLL(){
        orderDAO=new OrderDAO();
        productDAO=new ProductDAO();
        clientDAO=new ClientDAO();
        validators=new ArrayList<>();
        validators.add(new OrderQuantityValidator());
        validators.add(new OrderStockValidator(this.productDAO));
    }

    public List<Orders> getOrders(){
        return orderDAO.findAll();
    }

    public Orders addOrder(Orders order){
        Client client=clientDAO.findById(order.getClient_id());
        if(client==null)
        {
            throw new NoSuchElementException("Client with id "+order.getClient_id()+" doesnt exist");
        }
        for(Validator<Orders> v : validators)
        {
            v.validate(order);
        }
        Product produs=productDAO.findById(order.getProduct_id());
        double total=order.getQuantity()*produs.getPrice();
        order.setTotal_price(total);
        produs.setStock(produs.getStock()-order.getQuantity());
        productDAO.update(produs);
        return orderDAO.insert(order);
    }
}
