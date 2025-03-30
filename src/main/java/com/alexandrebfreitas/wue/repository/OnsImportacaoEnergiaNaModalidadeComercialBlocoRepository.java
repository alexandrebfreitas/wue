package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsImportacaoEnergiaNaModalidadeComercialBlocoRepository
    extends JpaRepository<OnsImportacaoEnergiaNaModalidadeComercialBlocoEntity, Long> {}
