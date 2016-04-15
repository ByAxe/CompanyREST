package net.nvcm.service.implementation;

import net.nvcm.core.dto.OperationJdbcDTO;
import net.nvcm.jdbc.Operations;
import net.nvcm.repository.interfaces.OperationRepository;
import net.nvcm.service.interfaces.OperationJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static net.nvcm.service.OperationTransformer.transformOperationToDTO;

/**
 * Created by byaxe on 4/15/16.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OperationJdbcServiceImpl implements OperationJdbcService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public void create(final String title, final Integer cost) {
        operationRepository.create(title, cost);
    }

    @Override
    public OperationJdbcDTO getOperation(final Integer id) {
        OperationJdbcDTO operationJdbcDTO = null;

        try {
            operationJdbcDTO = transformOperationToDTO(operationRepository.getOperation(id));
        } catch (NullPointerException ignored) {
        }

        return operationJdbcDTO;
    }

    @Override
    public List<OperationJdbcDTO> listOperations() {

        List<OperationJdbcDTO> transformedList = new ArrayList<>();

        for (Operations operation : operationRepository.listOperations()) {
            OperationJdbcDTO operationDTO = transformOperationToDTO(operation);

            transformedList.add(operationDTO);
        }
        return transformedList;
    }

    @Override
    public void delete(final Integer id) {
        operationRepository.delete(id);
    }

    @Override
    public void update(final Integer id, final String title, final Integer cost) {
        operationRepository.update(id, title, cost);
    }
}
