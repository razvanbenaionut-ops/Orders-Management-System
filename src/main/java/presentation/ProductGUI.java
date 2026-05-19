package presentation;

import Model.Client;
import Model.Product;
import businessLayer.ProductBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductGUI {
    private JPanel panel1;
    private JTextField idField;
    private JTextField priceField;
    private JTable table1;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTextField stockField;
    private JTextField nameField;
    private JFrame frame;
    private ProductBLL productBLL=new ProductBLL();
    public ProductGUI()
    {
        frame=new JFrame(" ");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        table1.setModel(ReflectionTable.createTable(productBLL.getProducts()));
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Product product=new Product(0,nameField.getText(),Double.parseDouble(priceField.getText()),Integer.parseInt(stockField.getText()));
                    productBLL.addProduct(product);
                    table1.setModel(ReflectionTable.createTable(productBLL.getProducts()));
                    idField.setText("");
                    nameField.setText("");
                    priceField.setText("");
                    stockField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ ex.getMessage());
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow=table1.getSelectedRow();
                    if(selectedRow==-1) {
                        JOptionPane.showMessageDialog(null,"Havent selected a product to edit");
                        return;
                    }
                    int id=Integer.parseInt(table1.getValueAt(selectedRow,0).toString());
                    String newName=nameField.getText();
                    Double newPrice=Double.parseDouble(priceField.getText());
                    int newStock=Integer.parseInt(stockField.getText());
                    if (newName.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Not selected a name to edit");
                        return;
                    }
                    Product product=new Product(id,newName,newPrice,newStock);
                    productBLL.editProduct(product);
                    table1.setModel(ReflectionTable.createTable(productBLL.getProducts()));
                    idField.setText("");
                    nameField.setText("");
                    priceField.setText("");
                    stockField.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow=table1.getSelectedRow();
                    if (selectedRow==-1) {
                        JOptionPane.showMessageDialog(null,"Havent selected a product to delete");
                        return;
                    }
                    int id=Integer.parseInt(table1.getValueAt(selectedRow,0).toString());
                    String name=table1.getValueAt(selectedRow,1).toString();
                    Double price=Double.parseDouble(table1.getValueAt(selectedRow,2).toString());
                    int stock=Integer.parseInt(table1.getValueAt(selectedRow,3).toString());
                    Product product=new Product(id,name,price,stock);
                    productBLL.deleteProduct(product);
                    table1.setModel(ReflectionTable.createTable(productBLL.getProducts()));
                    idField.setText("");
                    nameField.setText("");
                    priceField.setText("");
                    stockField.setText("");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error"+ex.getMessage());
                }
            }
        });
    }
}
