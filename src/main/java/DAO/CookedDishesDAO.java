package DAO;

import tables.CookedDishes;
import java.util.Date;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 23.08.2016.
 */
public class CookedDishesDAO implements TableDAO<CookedDishes> {

    static int MAX_ID;

    public void enteringInformation(Statement statement) throws SQLException {

        OrdersDAO ordersDAO = new OrdersDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        DishesDAO dishesDAO = new DishesDAO();
        Scanner scanner = new Scanner(System.in);

        int id = ++MAX_ID;

        dishesDAO.showAllPositions();
        System.out.println("Введите ID блюда :");
        int dishId = Integer.parseInt(scanner.nextLine());

        employeeDAO.showAllCooks();
        System.out.println("Введите ID повара :");
        int cookId = Integer.parseInt(scanner.nextLine());

        ordersDAO.showOpenedOrders();
        System.out.println("Введите ID заказа :");
        int orderId = Integer.parseInt(scanner.nextLine());

        Date date = new Date();

        String sql = "INSERT INTO Cooked_Dishes VALUES (" + id + ", " + dishId + ", " + cookId + ", " + orderId + ", " + date + ")";
        statement.execute(sql);

    }

    @Override
    public void addNewPosition() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT MAX(ID) FROM cooked_dishes";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                MAX_ID = resultSet.getInt(1);
            }
            enteringInformation(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletePositionByID() {

    }

    @Override
    public void showAllPositions() {

        List<CookedDishes> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM cooked_Dishes";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                list.add(createObject(resultSet));
            }
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findPositionByName(String name) {

    }

    @Override
    public CookedDishes createObject(ResultSet resultSet) throws SQLException {

        CookedDishes cookedDishes = new CookedDishes();

        cookedDishes.setID(resultSet.getInt("ID"));
        cookedDishes.setEmployeeID(resultSet.getInt("employee_ID"));
        cookedDishes.setDishID(resultSet.getInt("dish_ID"));
        cookedDishes.setOrderID(resultSet.getInt("order_ID"));
        cookedDishes.setDate(resultSet.getDate("date"));

        return cookedDishes;
    }
}
