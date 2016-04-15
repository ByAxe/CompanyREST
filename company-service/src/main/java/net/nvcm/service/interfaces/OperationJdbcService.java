package net.nvcm.service.interfaces;

import net.nvcm.core.dto.OperationJdbcDTO;

import java.util.List;

/**
 * Created by byaxe on 4/15/16.
 */
public interface OperationJdbcService {

    void create(final String title, final Integer cost);

    OperationJdbcDTO getOperation(final Integer id);

    List<OperationJdbcDTO> listOperations();

    void delete(final Integer id);

    void update(final Integer id, final String title, final Integer cost);

}
