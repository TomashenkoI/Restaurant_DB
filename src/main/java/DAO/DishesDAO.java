package DAO;

import tables.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 21.08.2016.
 */
public class DishesDAO implements TableDAO<Dish> {

    public static int MAX_ID;

    private ListOfIngredientsDAO listOfIngredientsDAO = new ListOfIngredientsDAO();

    public void enteringInformationForNew(Statement statement) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        int categoryID = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите название блюда :");
        String name = scanner.nextLine();

        System.out.println("Введите перечень ингредиентов :");

        listOfIngredientsDAO.addNewPosition();

        int listOfIngredientsID = listOfIngredientsDAO.ID_MAX;

        System.out.println("Введите цену :");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.println("Введите вес блюда :");
        int weight = Integer.parseInt(scanner.nextLine());

        int id = ++MAX_ID;
        String sql = "INSERT INTO dishes VALUES (" + id + ", '" + name + "', " + categoryID + ", " + listOfIngredientsID + ", " +
                    price + ", " + weight + ")";
        statement.execute(sql);
    }

    public void addNewPosition() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){

            String sql = "SELECT MAX(ID) FROM dishes";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                MAX_ID = resultSet.getInt(1);
            }

            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.showAllPositions();

            System.out.println("Введите ID категории :");
            enteringInformationForNew(statement);
            System.out.println("Блюдо добавлено!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePositionByID(){

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){

            showAllPositions();

            System.out.println("Введите ID блюда для удаления :");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());

            String sql = "DELETE FROM dishes WHERE ID = " + id;
            statement.execute(sql);
            System.out.println("Блюдо удалено!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void findPositionByName(String name){

        List<Dish> dishes = new ArrayList<>();

        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM dishes WHERE name LIKE '%" + name + "%'";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Dish dish = createObject(resultSet);
                dishes.add(dish);
            }
            if (!dishes.isEmpty()) {
                System.out.println("Блюда по запросу - '" + name + "' :");
            } else {
                System.out.println("Нет совпадений");
            }
            dishes.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllPositions() {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM dishes";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Dish dish = createObject(resultSet);
                System.out.println(dish.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Dish createObject(ResultSet resultSet) throws SQLException {

        Dish dish = new Dish();

        dish.setID(resultSet.getInt("ID"));
        dish.setCategoryOfDishes_ID(resultSet.getInt("categoryOfDishes_ID"));
        dish.setListOfIngredients_ID(resultSet.getInt("listOfIngredients_ID"));
        dish.setNameOfTheDish(resultSet.getString("name"));
        dish.setPrice(resultSet.getDouble("price"));
        dish.setWeight(resultSet.getInt("weight"));

        return dish;
    }

}
