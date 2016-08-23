package DAO;

import tables.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 22.08.2016.
 */
public class IngredientsDAO implements TableDAO<Ingredient> {

    static int MAX_ID;

    public static int getMaxId() {
        return MAX_ID;
    }

    public void enteringInformation(Statement statement) throws SQLException {

        int id = ++MAX_ID;

        System.out.println("Введите название ингридиента");
        String name = new Scanner(System.in).nextLine();

        String sql = "INSERT INTO ingredients VALUES (" + id + ", '" + name + "')";
        statement.execute(sql);
    }

    @Override
    public void addNewPosition() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT MAX(ID) FROM ingredients";
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

        List<Ingredient> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){

            String sql = "SELECT * FROM ingredients";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Ingredient ingredient = createIngredient(resultSet);
                list.add(ingredient);
            }
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void findPositionByName(String name) {

    }

    public Ingredient createIngredient(ResultSet resultSet) throws SQLException {

        Ingredient ingredient = new Ingredient();

        ingredient.setID(resultSet.getInt("ID"));
        ingredient.setName(resultSet.getString("Name"));

        return ingredient;
    }

    @Override
    public Ingredient createObject(ResultSet resultSet) throws SQLException {
        return null;
    }
}
