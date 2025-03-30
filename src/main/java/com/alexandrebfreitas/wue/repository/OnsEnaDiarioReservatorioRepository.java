package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsEnaDiarioReservatorioEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsEnaDiarioReservatorioEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsEnaDiarioReservatorioRepository extends JpaRepository<OnsEnaDiarioReservatorioEntity, Long> {}
