package DAO;

import tables.Storage;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import static DAO.Requests.*;

/**
 * Created by 7 on 23.08.2016.
 */
public class StorageDAO implements TableDAO<Storage>{

    static int MAX_ID;

    public void enteringInformation(Statement statement) throws SQLException {

        IngredientsDAO ingredientsDAO = new IngredientsDAO();
        boolean correct = false;
        int enteredDigit;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите : "+ "\n" +"1 - для добавления ингридиента из списка" +
                            "\n2 - для добавления нового" +
                            "\n3 - для выхода");

        while (true) {
            enteredDigit = Integer.parseInt(scanner.nextLine());
            if (enteredDigit == 1 || enteredDigit == 2 || enteredDigit == 3) {
                break;
            } else {
                System.out.println("Неверный ввод. Повторите попытку !");
            }
        }

        int id = ++MAX_ID;
        int ingredientId = 0;
        int amount = 0;

        if (enteredDigit == 1) {
            ingredientsDAO.showAllPositions();
            System.out.println("Выбирите ингридиент из списка");
            ingredientId = Integer.parseInt(scanner.nextLine());
        }
        if (enteredDigit == 2) {
            ingredientsDAO.addNewPosition();
            ingredientId = ingredientsDAO.MAX_ID;
        }
        if (enteredDigit < 3) {
            System.out.println("Введите колличество :");
            amount = Integer.parseInt(scanner.nextLine());

            String sql = "INSERT INTO storage VALUES (" + id + ", " + ingredientId + ", " + amount + ")";
            statement.execute(sql);
        }
    }

    @Override
    public void addNewPosition(){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT MAX(ID) FROM storage";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                MAX_ID = resultSet.getInt(1);
            }
            enteringInformation(statement);
            System.out.println("Ингридиент добавлен на склад! ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deletePositionByID() {

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            showAllPositions();

            System.out.println("Введите ID ингридиента для удаления: ");
            String enteredString = new Scanner(System.in).nextLine();

            String sql = "DELETE FROM storage WHERE id = " + enteredString;
            statement.execute(sql);
            System.out.println("Ингридиент удален!");
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    @Override
    public void showAllPositions() {

        ArrayList<Storage> storage = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM storage";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                storage.add(createObject(resultSet));
            }
            storage.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception occured while connecting to DB : " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findPositionByName(String name) {

    }

    @Override
    public Storage createObject(ResultSet resultSet) throws SQLException {

        Storage storage = new Storage();

        storage.setID(resultSet.getInt("ID"));
        storage.setIngredient_ID(resultSet.getInt("ingredient_ID"));
        storage.setAmount(resultSet.getInt("amount"));

        return storage;
    }
}
