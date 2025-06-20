package com.pluralsight.dealership.db;

import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement addVehicleStatement = connection.prepareStatement("""
                INSERT INTO vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) 
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )""")) {
            addVehicleStatement.setString(1, vehicle.getVin());
            addVehicleStatement.setString(2, vehicle.getMake());
            addVehicleStatement.setString(3, vehicle.getModel());
            addVehicleStatement.setInt(4, vehicle.getYear());
            addVehicleStatement.setBoolean(5, vehicle.isSold());
            addVehicleStatement.setString(6, vehicle.getColor());
            addVehicleStatement.setString(7, vehicle.getVehicleType());
            addVehicleStatement.setInt(8, vehicle.getOdometer());
            addVehicleStatement.setDouble(9, vehicle.getPrice());

            int affectedRows = addVehicleStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Inserting vehicle failed, no rows affected.");
            }

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM vehicles WHERE VIN = ?")){
            removeStatement.setString(1, VIN);
            int rows = removeStatement.executeUpdate();
            if(rows == 0 ){
                throw new SQLException("Removing vehicle failed, no rows affected.");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {

        List<Vehicle> results = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM vehicles WHERE price BETWEEN ? AND ? ") ){
            statement.setDouble(1, minPrice);
            statement.setDouble(2,maxPrice);

            try (ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    do {
                        Vehicle vehicle = createVehicleFromResultSet(resultSet);
                        results.add(vehicle);
                    }while (resultSet.next());

                } else {
                    System.out.println("No Matches");
                }

            }
        }catch (SQLException e) {
            e.printStackTrace(); // Log or handle the SQL exception.
        }
        return results;
    }

    public List<Vehicle> searchByMakeModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
        PreparedStatement vehicleStatement = connection.prepareStatement("""
                SELECT *
                FROM vehicles 
                WHERE make = ? AND model = ?""")){
            vehicleStatement.setString(1, make);
            vehicleStatement.setString(2, model);
            ResultSet resultSet = vehicleStatement.executeQuery();
            while(resultSet.next()) {
                Vehicle vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);

            }


        }catch (SQLException e) {
            e.printStackTrace(); // Log or handle the SQL exception.
        }
        return vehicles;
    }

    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {

        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement vehicleStatement = connection.prepareStatement("""
                SELECT *
                FROM vehicles WHERE year BETWEEN ? AND ?""")){
            vehicleStatement.setInt(1, minYear);
            vehicleStatement.setInt(2, maxYear);
            ResultSet resultSet = vehicleStatement.executeQuery();
            while(resultSet.next()) {
                Vehicle vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);

            }
        }catch (SQLException e) {
            e.printStackTrace(); // Log or handle the SQL exception.
        }



        return vehicles;
    }

    public List<Vehicle> searchByColor(String color) {

        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement vehicleStatement = connection.prepareStatement("""
                SELECT *
                FROM vehicles WHERE color = ?""")){
            vehicleStatement.setString(1, color);
            ResultSet resultSet = vehicleStatement.executeQuery();
            while(resultSet.next()) {
                Vehicle vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);

            }
        }catch (SQLException e) {
            e.printStackTrace(); // Log or handle the SQL exception.
        }


        return vehicles;
    }


    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {

        List<Vehicle> vehicles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            PreparedStatement vehicleStatement = connection.prepareStatement("""
                SELECT *
                FROM vehicles WHERE odometer BETWEEN ? AND ?""")){
            vehicleStatement.setInt(1, minMileage);
            vehicleStatement.setInt(2, maxMileage);
            ResultSet resultSet = vehicleStatement.executeQuery();
            while(resultSet.next()) {
                Vehicle vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);

            }
        }catch (SQLException e) {
            e.printStackTrace(); // Log or handle the SQL exception.
        }


        return vehicles;
    }

    public List<Vehicle> searchByType(String type) {

        List<Vehicle> vehicles = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement vehicleStatement = connection.prepareStatement("""
                SELECT *
                FROM vehicles WHERE vehicleType = ?""")){
            vehicleStatement.setString(1, type);
            ResultSet resultSet = vehicleStatement.executeQuery();
            while(resultSet.next()) {
                Vehicle vehicle = createVehicleFromResultSet(resultSet);
                vehicles.add(vehicle);

            }
        }catch (SQLException e) {
            e.printStackTrace(); // Log or handle the SQL exception.
        }


        return vehicles;
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
