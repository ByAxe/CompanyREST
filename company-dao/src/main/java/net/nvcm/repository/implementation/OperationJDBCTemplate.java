package net.nvcm.repository.implementation;

import net.nvcm.jdbc.Operations;
import net.nvcm.repository.OperationMapper;
import net.nvcm.repository.interfaces.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by byaxe on 4/15/16.
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class OperationJDBCTemplate implements OperationRepository {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private String SQL;

    @Override
    public void setDataSource(final DataSource source) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(final String title, final Integer cost) {
        SQL = "INSERT INTO Operations (title, cost) VALUES (?,?)";

        jdbcTemplate.update(SQL, title, cost);
    }

    @Override
    public Operations getOperation(final Integer id) {
        SQL = "SELECT * FROM Operations WHERE id = ?";

        return jdbcTemplate.queryForObject(SQL, new Object[]{id}, new OperationMapper());
    }

    @Override
    public List<Operations> listOperations() {
        SQL = "SELECT * FROM Operations";

        return jdbcTemplate.query(SQL, new OperationMapper());
    }

    @Override
    public void delete(final Integer id) {
        SQL = "DELETE FROM Operations WHERE id = ?";

        jdbcTemplate.update(SQL, id);
    }

    @Override
    public void update(final Integer id, final String title, final Integer cost) {
        SQL = "UPDATE Operations SET title = ?, cost = ? WHERE id = ?";

        jdbcTemplate.update(SQL, title, cost, id);
    }
}
