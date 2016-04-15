package net.nvcm.repository.interfaces;

import net.nvcm.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by byaxe on 4/8/16.
 */
public interface CompanyRepository extends JpaRepository<CompanyEntity, Integer>, CompanyRepositoryCustom {

    CompanyEntity findByTitle (final String title);

}
