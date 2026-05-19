package presentation;

import Model.Bill;
import Model.Orders;
import Model.Product;
import businessLayer.ClientBLL;
import businessLayer.OrderBLL;
import businessLayer.ProductBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderGUI {
    private JButton orderButton;
    private JTable table1;
    private JTable table2;
    private JTextField textField1;
    private JPanel orderPanel;
    private JFrame frame;
    private OrderBLL orderBLL = new OrderBLL();
    private ProductBLL productBLL = new ProductBLL();
    private ClientBLL  clientBLL = new ClientBLL();
    public OrderGUI() {
        frame = new JFrame();
        frame.setContentPane(orderPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        table1.setName("Products");
        table2.setName("Clients");
        table1.setModel(ReflectionTable.createTable(productBLL.getProducts()));
        table2.setModel(ReflectionTable.createTable(clientBLL.getClients()));
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int productRow=table1.getSelectedRow();
                    if (productRow==-1) {
                        JOptionPane.showMessageDialog(null,"Havent selected a product");
                        return;
                    }
                    int clientRow=table2.getSelectedRow();
                    if (clientRow==-1) {
                        JOptionPane.showMessageDialog(null,"Havent selected a client");
                        return;
                    }
                    String quantitytxt=textField1.getText();
                    if (quantitytxt.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Not written quantity");
                        return;
                    }
                    int clientId=Integer.parseInt(table2.getValueAt(clientRow,0).toString());
                    int productId=Integer.parseInt(table1.getValueAt(productRow,0).toString());
                    int quantity=Integer.parseInt(textField1.getText());
                    if (quantity<=0) {
                        JOptionPane.showMessageDialog(null,"Quantity must be greater than 0");
                        return;
                    }
                    Product produs=productBLL.findProduct(productId);
                    if(produs.getStock()<quantity) {
                        JOptionPane.showMessageDialog(null,"Not enough stock");
                        return;
                    }
                    double totalPrice=produs.getPrice()*quantity;
                    Orders order=new Orders(0,clientId,productId,totalPrice,quantity);
                    Orders finalOrder=orderBLL.addOrder(order);
                    String chitanta="Client: "+clientBLL.findClient(clientId).getName()+"\nProduct: "+produs.getName()+"\nQuantity: "+quantity+"\nTotal Price: "+totalPrice;
                    JOptionPane.showMessageDialog(null,chitanta,"Reciept",JOptionPane.INFORMATION_MESSAGE);
                    textField1.setText("");
                    table1.setModel(ReflectionTable.createTable(productBLL.getProducts()));
                    table2.setModel(ReflectionTable.createTable(clientBLL.getClients()));
                }catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }catch(Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
    }
}
