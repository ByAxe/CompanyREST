package net.nvcm.service;

import net.nvcm.core.dto.CompanyDTOFull;
import net.nvcm.entities.CompanyEntity;

/**
 * Created by byaxe on 4/12/16.
 */
public class CompanyTransformer {

    public static CompanyDTOFull transformCompanyEntityToDTO(final CompanyEntity companyEntity) {
        return new CompanyDTOFull(
                companyEntity.getId(),
                companyEntity.getTitle(),
                companyEntity.getSlogan());
    }


    public static CompanyEntity transformCompanyDTOToEntity(final CompanyDTOFull companyDTO) {
        return new CompanyEntity(
                companyDTO.getTitle(),
                companyDTO.getSlogan());
    }
}
