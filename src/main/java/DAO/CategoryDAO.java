package DAO;

import tables.CategoryOfDishes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static DAO.Requests.*;

/**
 * Created by 7 on 22.08.2016.
 */
public class CategoryDAO implements TableDAO<CategoryOfDishes>{

    public void showAllPositions() {

        List<CategoryOfDishes> list = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()){
            String sql = "SELECT * FROM category_of_dishes";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                CategoryOfDishes categoryOfDishes = createObject(resultSet);
                list.add(categoryOfDishes);
            }
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNewPosition() {

    }

    @Override
    public void deletePositionByID() {

    }

    @Override
    public void findPositionByName(String name) {

    }

    @Override
    public CategoryOfDishes createObject(ResultSet resultSet) throws SQLException {

        CategoryOfDishes categoryOfDishes = new CategoryOfDishes();

        categoryOfDishes.setID(resultSet.getInt("ID"));
        categoryOfDishes.setName(resultSet.getString("name"));

        return categoryOfDishes;
    }
}
