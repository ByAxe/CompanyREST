package net.nvcm.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Created by byaxe on 4/8/16.
 */
public abstract class GenericAbstractDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "APU")
    protected EntityManager entityManager;
}
