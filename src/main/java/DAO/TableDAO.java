package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 7 on 22.08.2016.
 */
public interface TableDAO<T> {

    void addNewPosition();

    void deletePositionByID();

    void showAllPositions();

    void findPositionByName(String name);

    T createObject(ResultSet resultSet) throws SQLException;

}
