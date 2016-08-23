package DAO;

import tables.Orders;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 23.08.2016.
 */
public class OrdersDAO implements TableDAO<Orders> {

    static int MAX_ID;

    public void enteringInformation(Statement statement) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDAO();
        ListOfDishesDAO listOfDishesDAO = new ListOfDishesDAO();

        Scanner scanner = new Scanner(System.in);

        int id = ++MAX_ID;

        employeeDAO.showAllCooks();
        System.out.println("Введите ID повара :");
        int cookId = Integer.parseInt(scanner.nextLine());

        listOfDishesDAO.addNewPosition();
        int listOfDishesId = listOfDishesDAO.ID_MAX;

        System.out.println("Введите номер столика :");
        int tableNumber = Integer.parseInt(scanner.nextLine());

        Date date = new Date();

        String sql = "INSERT INTO orders VALUES (" + id + ", " + cookId + ", " + listOfDishesId + ", " + tableNumber + ", " + date + ", true)";

        statement.execute(sql);

    }

    @Override
    public void addNewPosition() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT MAX(ID) FROM employees";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                MAX_ID = resultSet.getInt(1);
            }
            enteringInformation(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editTheOrder() {

    }

    @Override
    public void deletePositionByID() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){

            showOpenedOrders();

            System.out.println("Введите ID заказа для удаления :");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());

            String sql = "DELETE FROM orders WHERE id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void CloseOrderByID() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            showOpenedOrders();

            System.out.println("Введите ID заказа для закрытия :");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());

            String sql = "UPDATE orders SET Access = false WHERE id = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showAllPositions() {

        List<Orders> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM orders";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                orders.add(createObject(resultSet));
            }
            orders.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showOpenedOrders() {

        List<Orders> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM orders WHERE access = true";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                orders.add(createObject(resultSet));
            }
            orders.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showClosedOrders() {

        List<Orders> orders = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM orders WHERE access = false";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                orders.add(createObject(resultSet));
            }
            orders.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findPositionByName(String name) {

    }

    @Override
    public Orders createObject(ResultSet resultSet) throws SQLException {

        Orders orders = new Orders();

        orders.setID(resultSet.getInt("ID"));
        orders.setDate(resultSet.getDate("Date"));
        orders.setEmployeeID(resultSet.getInt("employee_ID"));
        orders.setListOfDishesID(resultSet.getInt("listOfDishes_ID"));
        orders.setTableNumber(resultSet.getInt("tableNumber"));

        return null;
    }

}
