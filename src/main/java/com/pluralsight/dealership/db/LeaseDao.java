package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaseDao {
    private DataSource dataSource;

    public LeaseDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addLeaseContract(LeaseContract leaseContract) {

        try(Connection connection = dataSource.getConnection();
        PreparedStatement leaseStatement = connection.prepareStatement("""
                        INSERT INTO lease_contracts (VIN, lease_start, lease_end, monthly_payment)
                        VALUES (?, ?, ?, ?)
                        """,
                PreparedStatement.RETURN_GENERATED_KEYS )){
            leaseStatement.setString(1, leaseContract.getVin());
            leaseStatement.setDate(2, java.sql.Date.valueOf(leaseContract.getLeaseStart())); // this value of help sql workbench understand how to formate the date making it easier for sql to understand
            leaseStatement.setDate(3, java.sql.Date.valueOf(leaseContract.getLeaseEnd())); // this value of help sql workbench understand how to formate the date making it easier for sql to understand
            leaseStatement.setDouble(4,leaseContract.getMonthlyPayment());
            leaseStatement.executeUpdate();

            try(ResultSet generateKeys = leaseStatement.getGeneratedKeys()){
                if (generateKeys.next()){
                    int contractID = generateKeys.getInt(1);
                    System.out.println("New Contract ID: " + contractID);

                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
