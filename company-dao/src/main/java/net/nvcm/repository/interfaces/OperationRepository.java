package net.nvcm.repository.interfaces;

import net.nvcm.jdbc.Operations;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by byaxe on 4/15/16.
 */
public interface OperationRepository {

    void setDataSource(DataSource source);

    void create(final String title, final Integer cost);

    Operations getOperation(final Integer id);

    List<Operations> listOperations();

    void delete(final Integer id);

    void update(final Integer id, final String title, final Integer cost);


}
