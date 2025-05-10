package com.akimmin.top.car;

import java.sql.*;
import java.util.*;

/**
 * класс для рабы с базой данных
 */
public abstract class jdbcUtil {


    private static final String URL = "jdbc:postgresql://localhost:5432/car";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "admin";
    private static final String SELECT_ALL = "SELECT * FROM cars";
    private static final String INSERT = "Insert into cars(manufacturerName,carName,volume,yearOfManufacturer,color,carType) Values(?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM CARS WHERE carname = ?";
    private static final String UPDATE = "UPDATE CARS SET color = ? WHERE carname = ?";

    Scanner scanner = CarService.scanner;

    protected void printAllCars() {
        try {
            Class.forName("org.postgresql.Driver");

            try (
                    Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    System.out.println("manufacturerName= " + resultSet.getString("manufacturerName") + "  " + "carName = " + resultSet.getString("carName") + "volume= " + resultSet.getFloat("volume") + "  " + "yearOfManufacturer= " + resultSet.getInt("yearOfManufacturer") + " " + "color= " + resultSet.getString("color") + "  " + "carType= " + resultSet.getString("carType"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    abstract void addCar(String manufacturerName, String carName, float volume, int yearOfManufacturer, String color);

    public void showAllCarMarks() {

        Set<String> carMarks = new HashSet<String>();
        try (
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                carMarks.add(resultSet.getString("manufacturerName"));
            }
            carMarks.forEach(System.out::println);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    void manufacturerCounter() {

        Set<String> carMarks = new HashSet<String>();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayList2 = new ArrayList<>();

        StringBuilder sb = new StringBuilder();

        try (
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                carMarks.add(resultSet.getString("manufacturerName"));
                arrayList.add(resultSet.getString("manufacturerName"));

            }
            for (String element : carMarks) {
                int count = Collections.frequency(arrayList, element);
                sb.append(element).append("= ").append(count).append(" ");

            }
            System.out.println(sb);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    void showCarsInManufacturerRange(int min, int max) {

        try (
                Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                if (resultSet.getInt("yearOfManufacturer") >= min && resultSet.getInt("yearOfManufacturer") <= max) {
                    System.out.println("manufacturerName= " + resultSet.getString("manufacturerName") + "  " + "carName = " + resultSet.getString("carName") + "volume= " + resultSet.getFloat("volume") + "  " + "yearOfManufacturer= " + resultSet.getInt("yearOfManufacturer") + " " + "color= " + resultSet.getString("color") + "  " + "carType= " + resultSet.getString("carType"));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void showCarByType() {
        System.out.println("Введите тип кузова : sedan ,wagon...");
        String carType = scanner.nextLine();
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = ps.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getString("carType").equals(carType)) {
                    System.out.println("manufacturerName= " + resultSet.getString("manufacturerName") + "  " + "carName = " + resultSet.getString("carName") + "volume= " + resultSet.getFloat("volume") + "  " + "yearOfManufacturer= " + resultSet.getInt("yearOfManufacturer") + " " + "color= " + resultSet.getString("color") + "  " + "carType= " + resultSet.getString("carType"));

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            scanner.close();
        }


    }

   public static void addCar(String manufacturerName,String carName,float volume,int yearOfManufacturer,String color,String carType) {
        System.out.println("---Добавление нового автомобиля в таблицу---");
        try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(INSERT);
        ) {



            ps.setString(1, manufacturerName);
            ps.setString(2, carName);
            ps.setFloat(3, volume);
            ps.setInt(4, yearOfManufacturer);
            ps.setString(5, color);
            ps.setString(6, carType);

            ps.executeUpdate();

            System.out.println("Автомобиль успешно добавлен!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     public static void deleteCar(String carName) {

         try (Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
              PreparedStatement ps = connection.prepareStatement(DELETE)) {
             ps.setString(1, carName);
             int rowsDeleted = ps.executeUpdate();
             System.out.println(rowsDeleted + " row(s) deleted.");

         } catch (SQLException e) {
             throw new RuntimeException(e);
         }


     }
    void updateColor () {
try(Connection connection=DriverManager.getConnection(URL,LOGIN,PASSWORD);
PreparedStatement ps=connection.prepareStatement(UPDATE)) {
    System.out.println("---Изменение цвета втомоблия---");
    System.out.println("Введите название атомобиля");
    String carName = scanner.nextLine();
    System.out.println("Введите цвет автомобиля");
    String color = scanner.nextLine();
    ps.setString(1,color );
    ps.setString(2,carName );
    int rowsUpdated = ps.executeUpdate();
    System.out.println(rowsUpdated + " row(s) updated.");

} catch (SQLException e) {
    throw new RuntimeException(e);
}finally {
    scanner.close();
}


    }
}




