package net.nvcm.service;

import net.nvcm.core.dto.OperationJdbcDTO;
import net.nvcm.jdbc.Operations;

/**
 * Created by byaxe on 4/15/16.
 */
public class OperationTransformer {

    public static OperationJdbcDTO transformOperationToDTO(final Operations operations) {
        return new OperationJdbcDTO(
                operations.getId(),
                operations.getTitle(),
                operations.getCost());
    }

}
