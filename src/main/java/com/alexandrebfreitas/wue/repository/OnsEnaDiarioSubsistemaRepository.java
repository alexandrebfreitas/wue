package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsEnaDiarioSubsistemaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsEnaDiarioSubsistemaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsEnaDiarioSubsistemaRepository extends JpaRepository<OnsEnaDiarioSubsistemaEntity, Long> {}
