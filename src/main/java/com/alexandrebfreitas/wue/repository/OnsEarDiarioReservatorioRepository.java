package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsEarDiarioReservatorioEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsEarDiarioReservatorioEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsEarDiarioReservatorioRepository extends JpaRepository<OnsEarDiarioReservatorioEntity, Long> {}
