package DAO;

import tables.Positions;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by 7 on 21.08.2016.
 */
public class PositionsDAO {

    public static Positions createPosition(ResultSet resultSet) throws SQLException {

        Positions position = new Positions();

        position.setID(resultSet.getInt("ID"));
        position.setName(resultSet.getString("Name"));

        return position;
    }

}
