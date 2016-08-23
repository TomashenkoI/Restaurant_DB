package DAO;

import tables.ListOfDishes;

import java.sql.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 23.08.2016.
 */
public class ListOfDishesDAO implements TableDAO<ListOfDishes> {

    public static int ID_MAX;

    DishesDAO dishesDAO = new DishesDAO();

    public void enteringInformation(Statement statement) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        String enteredString;

        for (int i = 0; i < 10; i++) {
            dishesDAO.showAllPositions();
            System.out.println("Выбирите номер блюда #" + (i+1) + " или введите 'exit' :");
            enteredString = scanner.nextLine();
            if (enteredString.equals("exit")) break;
            deque.add(Integer.parseInt(enteredString));
        }

        int maxID = ++ID_MAX;

        StringBuffer sql = new StringBuffer("INSERT INTO list_of_dishes VALUES (" + maxID);
        while (!deque.isEmpty()){
            sql.append(", " + deque.pollFirst());
        }
        sql.append(")");

        statement.execute(sql.toString());

    }

    @Override
    public void addNewPosition() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT MAX(ID) FROM list_of_dishes";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ID_MAX = resultSet.getInt(1);
            }

            enteringInformation(statement);
            System.out.println("Список добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePositionByID() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            showAllPositions();

            System.out.println("Введите ID списка блюд для удаления :");
            int ID = Integer.parseInt(new Scanner(System.in).nextLine());

            String sql = "DELETE FROM list_of_dishes WHERE ID = " + ID;
            statement.execute(sql);
            System.out.println("Список блюд удален!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showAllPositions() {

        List<ListOfDishes> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM list_of_dishes";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
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
    public ListOfDishes createObject(ResultSet resultSet) throws SQLException {

        ListOfDishes list = new ListOfDishes();

        list.setID(resultSet.getInt("ID"));
        list.setDishID_N1(resultSet.getInt("Dish_id_n1"));
        list.setDishID_N2(resultSet.getInt("Dish_id_n2"));
        list.setDishID_N3(resultSet.getInt("Dish_id_n3"));
        list.setDishID_N4(resultSet.getInt("Dish_id_n4"));
        list.setDishID_N5(resultSet.getInt("Dish_id_n5"));
        list.setDishID_N6(resultSet.getInt("Dish_id_n6"));
        list.setDishID_N7(resultSet.getInt("Dish_id_n7"));
        list.setDishID_N8(resultSet.getInt("Dish_id_n8"));
        list.setDishID_N9(resultSet.getInt("Dish_id_n9"));
        list.setDishID_N10(resultSet.getInt("Dish_id_n10"));

        return list;
    }

}

