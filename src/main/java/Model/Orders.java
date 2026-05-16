package Model;

public class Orders {

    private int id;
    private int client_id;
    private int product_id;
    private double total_price;
    private int quantity;

    public Orders(){

    }
    public Orders(int id,int client_id,int product_id,double total_price,int quantity){
        this.id=id;
        this.client_id=client_id;
        this.product_id=product_id;
        this.total_price=total_price;
        this.quantity=quantity;
    }

    public Orders(int client_id,int product_id,double total_price,int quantity){
        this.client_id=client_id;
        this.product_id=product_id;
        this.total_price=total_price;
        this.quantity=quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", client_id=" + client_id +
                ", total_price=" + total_price +
                '}';
    }
}
