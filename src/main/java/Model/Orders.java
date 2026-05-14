package Model;

public class Orders {

    private int id;
    private int client_id;
    private double total_price;

    public Orders(){

    }
    public Orders(int id,int client_id,double total_price){
        this.id=id;
        this.client_id=client_id;
        this.total_price=total_price;
    }

    public Orders(int client_id,double total_price){
        this.client_id=client_id;
        this.total_price=total_price;
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

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
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
