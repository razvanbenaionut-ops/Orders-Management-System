package businessLayer;

import Model.Client;
import Model.Product;
import businessLayer.validators.*;
import dataAccessLayer.ProductDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;
    public ProductBLL(){
        productDAO=new ProductDAO();
        validators=new ArrayList<Validator<Product>>();
        validators.add(new ProductNameValidator());
        validators.add(new ProductPriceValidator());
        validators.add(new ProductStockValidator());
    }

    public List<Product> getProducts(){
        return productDAO.findAll();
    }

    public Product findProduct(int id)
    {
       // Product p=productDAO.findById(id);
       // if(p==null)
        //{
          //  throw new NoSuchElementException("Product with id "+id+" doesnt exist");
        //}
        //return p;
        List<Product> produse=productDAO.findAll();
        return produse.stream().
                filter(p->p.getId()==id).
                findFirst().
                orElseThrow(()->new NoSuchElementException("Product with id "+id+" doesnt exist"));
    }

    public Product addProduct(Product produs)
    {
        //for(Validator<Product> p :validators)
        //{
          //  p.validate(produs);
       // }
        //return productDAO.insert(produs);
        validators.stream().
                forEach(v->v.validate(produs));
        return productDAO.insert(produs);
    }

    public void editProduct(Product produs)
    {
        //for(Validator<Product> p :validators)
        //{
          //  p.validate(produs);
        //}
        //productDAO.update(produs);
        validators.stream().
                forEach(v->v.validate(produs));
        productDAO.update(produs);
    }

    public void deleteProduct(Product produs)
    {
        productDAO.delete(produs);
    }
}
