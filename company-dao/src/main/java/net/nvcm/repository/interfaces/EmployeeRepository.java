package net.nvcm.repository.interfaces;

import net.nvcm.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer>, EmployeeRepositoryCustom {

    List<EmployeeEntity> findByName(final String name);

}
