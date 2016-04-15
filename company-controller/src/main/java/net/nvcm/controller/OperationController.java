package net.nvcm.controller;

import com.google.common.base.Optional;
import net.nvcm.core.dto.OperationJdbcDTO;
import net.nvcm.service.interfaces.OperationJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by byaxe on 4/15/16.
 */
@RestController
@RequestMapping(method = RequestMethod.GET)
public class OperationController {

    @Autowired
    private OperationJdbcService operationJdbcService;

    @RequestMapping(value = "/operations/{id}")
    public ResponseEntity<OperationJdbcDTO> getOperation(@PathVariable("id") final int id) {
        OperationJdbcDTO operation = operationJdbcService.getOperation(id);

        if (operation == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(operation, HttpStatus.OK);
    }


    @RequestMapping(value = "/operations")
    public ResponseEntity<List<OperationJdbcDTO>> listAllOperations() {
        List<OperationJdbcDTO> operations = operationJdbcService.listOperations();

        if (operations.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @RequestMapping(value = "/operations/", method = RequestMethod.POST)
    public ResponseEntity<Void> createOperation(@RequestBody OperationJdbcDTO operation, UriComponentsBuilder builder) {
        operationJdbcService.create(operation.getTitle(), operation.getCost());

        HttpHeaders headers = new HttpHeaders();

        headers.setLocation(builder.path("/operations/{id}").buildAndExpand(operation.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/operations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OperationJdbcDTO> deleteOperation(@PathVariable("id") final int id) {
        OperationJdbcDTO operation = operationJdbcService.getOperation(id);

        if (operation == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        operationJdbcService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/operations/{id}", method = RequestMethod.PUT)
    public ResponseEntity<OperationJdbcDTO> updateOperation(@PathVariable("id") final int id,
                                                            @RequestBody OperationJdbcDTO operation) {
        Optional<OperationJdbcDTO> operationOptional = Optional.fromNullable(operationJdbcService.getOperation(id));

        if (!operationOptional.isPresent()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        operationJdbcService.update(id, operation.getTitle(), operation.getCost());

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
