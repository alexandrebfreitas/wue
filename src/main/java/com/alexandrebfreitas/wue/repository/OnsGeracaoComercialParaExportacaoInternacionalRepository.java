package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsGeracaoComercialParaExportacaoInternacionalEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsGeracaoComercialParaExportacaoInternacionalEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsGeracaoComercialParaExportacaoInternacionalRepository
    extends JpaRepository<OnsGeracaoComercialParaExportacaoInternacionalEntity, Long> {}
