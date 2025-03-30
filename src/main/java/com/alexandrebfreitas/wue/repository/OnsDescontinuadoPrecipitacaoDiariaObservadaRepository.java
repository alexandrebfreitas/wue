package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsDescontinuadoPrecipitacaoDiariaObservadaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsDescontinuadoPrecipitacaoDiariaObservadaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsDescontinuadoPrecipitacaoDiariaObservadaRepository
    extends JpaRepository<OnsDescontinuadoPrecipitacaoDiariaObservadaEntity, Long> {}
