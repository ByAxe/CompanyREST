package net.nvcm.service;

import net.nvcm.core.dto.EmployeeDTOFull;
import net.nvcm.entities.EmployeeEntity;

/**
 * Created by byaxe on 4/12/16.
 */
public class EmployeeTransformer {

    public static EmployeeDTOFull transformEmployeeEntityToDTO(final EmployeeEntity employee) {
        return new EmployeeDTOFull(
                employee.getId(),
                employee.getName(),
                employee.getPosition(),
                employee.getSex(),
                employee.getAge());
    }

    public static EmployeeEntity transformEmployeeDTOToEntity(final EmployeeDTOFull employee) {
        return new EmployeeEntity(
                employee.getName(),
                employee.getPosition(),
                employee.getSex(),
                employee.getAge());
    }

}
