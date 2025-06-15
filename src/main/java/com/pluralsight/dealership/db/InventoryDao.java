package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {
        // TODO: Implement the logic to add a vehicle to the inventory

        try (Connection connection = dataSource.getConnection();
             PreparedStatement vehicleStatement = connection.prepareStatement("INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)")){
       // ResultSet resultSet = vehicleStatement.executeQuery();

            vehicleStatement.setInt(1, dealershipId);
            vehicleStatement.setString(2, vin);
            vehicleStatement.executeUpdate();

//            if(resultSet.next()) {
//                do {
//                    String vin = resultSet.getString(1);
//                    int dealershipID = resultSet.getDouble(2);
//
//                    inventory.add()
//
//                }while(resultSet.next());
//
//            }else {
//                System.out.println("No Matches");

//            }
        } catch(SQLException e){
            e.printStackTrace();
        }


    }

    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM inventory WHERE inventory = (?)")){
            preparedStatement.setString(2, vin);
            preparedStatement.executeUpdate();

        }catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
