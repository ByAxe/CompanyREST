package net.nvcm.repository;

import net.nvcm.jdbc.Operations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by byaxe on 4/15/16.
 */
public class OperationMapper implements RowMapper<Operations> {

    @Override
    public Operations mapRow(ResultSet resultSet, int i) throws SQLException {

        Operations operations = new Operations();

        final int id = resultSet.getInt("id");
        final String title = resultSet.getString("title");
        final int cost = resultSet.getInt("cost");

        operations.setId(id);
        operations.setTitle(title);
        operations.setCost(cost);

        return operations;
    }
}
