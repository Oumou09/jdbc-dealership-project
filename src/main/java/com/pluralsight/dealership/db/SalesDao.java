package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {
        // TODO: Implement the logic to add a sales contract
        try(Connection connection = dataSource.getConnection();
        PreparedStatement saleStatement = connection.prepareStatement("""
                INSERT INTO sales_contracts (VIN, sale_date, price)
                VALUES (?, ?, ?)""",
                PreparedStatement.RETURN_GENERATED_KEYS)){
            saleStatement.setString(1, salesContract.getVin());
            saleStatement.setDate(2, java.sql.Date.valueOf(salesContract.getSaleDate()));
            saleStatement.setDouble(3, salesContract.getPrice());
            saleStatement.executeUpdate();

            try (ResultSet generatedKeys = saleStatement.getGeneratedKeys()) {
                if(generatedKeys.next()){
                    int contractID = generatedKeys.getInt(1);
                    System.out.println("New Contract ID: " + salesContract.getContractId());

                }

            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
