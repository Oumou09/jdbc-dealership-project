package com.pluralsight.dealership.db;



import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement vehicleStatement = connection.prepareStatement("INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)")) {

            vehicleStatement.setInt(1, dealershipId);
            vehicleStatement.setString(2, vin);

            int rowsInserted = vehicleStatement.executeUpdate();
            if (rowsInserted == 0) {
                System.out.println("No rows inserted.");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicleFromInventory(String vin) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM inventory WHERE VIN = ?")){
            preparedStatement.setString(1, vin);
            int rowsDeleted = preparedStatement.executeUpdate();
            System.out.println("Rows deleted: " + rowsDeleted);

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
