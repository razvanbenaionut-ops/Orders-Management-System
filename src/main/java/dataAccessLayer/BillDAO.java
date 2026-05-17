package dataAccessLayer;

import Connection.ConnectionFactory;
import Model.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BillDAO {

    public void insertLog(Bill bill) {
        String query = "INSERT INTO log (name_client, name_produs, quantity, price_product, total_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, bill.name_client());
            statement.setString(2, bill.name_product());
            statement.setInt(3, bill.quantity());
            statement.setDouble(4, bill.price_product());
            statement.setDouble(5, bill.total_price());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}