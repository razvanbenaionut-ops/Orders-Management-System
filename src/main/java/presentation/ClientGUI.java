package presentation;

import Model.Client;
import businessLayer.ClientBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class ClientGUI {
    private JTextField textField1;
    private JTextField textField2;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JTable table1;
    private JPanel clientPanel;
    private JFrame frame;
    private ClientBLL clientBLL=new ClientBLL();
    public ClientGUI(){
        frame=new JFrame(" ");
        frame.setContentPane(clientPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        table1.setModel(ReflectionTable.createTable(clientBLL.getClients()));

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Client client=new Client(0,textField2.getText());
                    clientBLL.addClient(client);
                    table1.setModel(ReflectionTable.createTable(clientBLL.getClients()));
                    textField2.setText("");
                    textField1.setText("");
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
                        JOptionPane.showMessageDialog(null,"Havent selected a client to edit");
                        return;
                    }
                    int id=Integer.parseInt(table1.getValueAt(selectedRow,0).toString());
                    String noulNume=textField2.getText();
                    if (noulNume.isEmpty()) {
                        JOptionPane.showMessageDialog(null,"Not selected a name to edit");
                        return;
                    }
                    Client client=new Client(id,noulNume);
                    clientBLL.editClient(client);
                    table1.setModel(ReflectionTable.createTable(clientBLL.getClients()));
                    textField1.setText("");
                    textField2.setText("");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error" + ex.getMessage());
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int selectedRow=table1.getSelectedRow();
                    if (selectedRow==-1) {
                        JOptionPane.showMessageDialog(null,"Havent selected a client to delete");
                        return;
                    }
                    int id=Integer.parseInt(table1.getValueAt(selectedRow,0).toString());
                    String name=table1.getValueAt(selectedRow,1).toString();
                    Client client=new Client(id,name);
                    clientBLL.deleteClient(client);
                    table1.setModel(ReflectionTable.createTable(clientBLL.getClients()));
                    textField1.setText("");
                    textField2.setText("");
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error" + ex.getMessage());
                }
            }
        });

    }
}
