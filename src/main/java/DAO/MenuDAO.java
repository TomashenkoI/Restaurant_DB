package DAO;

import tables.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 23.08.2016.
 */
public class MenuDAO implements TableDAO<Menu>{

    static int ID_MAX;

    private ListOfDishesDAO listOfDishesDAO = new ListOfDishesDAO();

    public void enteringInformation(Statement statement) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        listOfDishesDAO.addNewPosition();

        System.out.println("Введите название меню :");
        String name = scanner.nextLine();

        String sql = "INSERT INTO menu VALUES (" + (++ID_MAX) + ", '" + name + "', " + listOfDishesDAO.ID_MAX + ")";

        statement.execute(sql.toString());

    }

    @Override
    public void addNewPosition() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT MAX(ID) FROM menu";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                ID_MAX = resultSet.getInt(1);
            }

            enteringInformation(statement);
            System.out.println("Меню добавлено");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePositionByID() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement()){

            showAllPositions();

            System.out.println("Введите ID меню для удаления :");
            int id = Integer.parseInt(new Scanner(System.in).nextLine());

            String sql = "DELETE FROM menu WHERE ID = " + id;
            statement.execute(sql);
            System.out.println("Меню удалено!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void showAllPositions() {

        List<Menu> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM menu";
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

        List<Menu> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM menu WHERE name LIKE '%" + name + "%'";
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
    public Menu createObject(ResultSet resultSet) throws SQLException {

        Menu menu = new Menu();

        menu.setID(resultSet.getInt("ID"));
        menu.setName(resultSet.getString("Name"));
        menu.setListOfDishes_ID(resultSet.getInt("listOfDishes_ID"));

        return menu;
    }
}
