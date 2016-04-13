package net.nvcm.repository.implementation;

import net.nvcm.entities.EmployeeEntity;
import net.nvcm.repository.GenericAbstractDAO;
import net.nvcm.repository.interfaces.IEmployeeRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by byaxe on 4/8/16.
 */
@Repository
@Transactional(readOnly = true)
public class EmployeeRepositoryImpl extends GenericAbstractDAO implements IEmployeeRepositoryCustom {

    @Override
    public boolean isExist(final EmployeeEntity employee) {
        EmployeeEntity ourEmployee = null;

        TypedQuery<EmployeeEntity> typedQuery = entityManager.createQuery("SELECT e FROM EmployeeEntity e" +
                " WHERE e.name = :name AND e.position = :position" +
                " AND e.sex = :sex AND e.age = :age", EmployeeEntity.class);


        typedQuery.setParameter("name", employee.getName())
                .setParameter("position", employee.getPosition())
                .setParameter("sex", employee.getSex())
                .setParameter("age", employee.getAge());

        try {
            ourEmployee = typedQuery.getSingleResult();
        } catch (Throwable throwable) {
//            throwable.printStackTrace();
        }

        return ourEmployee != null;
    }

    @Override
    public EmployeeEntity saveEmployeeToCompany(int company_id, final EmployeeEntity employee) {
        return null;
    }

    @Override
    public List<EmployeeEntity> getEmployeesListById(final int id) {
        return entityManager.createQuery("SELECT e FROM EmployeeEntity e JOIN" +
                " e.companies c WHERE c.id = :id", EmployeeEntity.class).setParameter("id", id)
                .getResultList();
    }
}
