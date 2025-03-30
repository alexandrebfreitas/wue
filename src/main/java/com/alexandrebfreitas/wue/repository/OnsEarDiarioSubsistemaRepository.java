package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsEarDiarioSubsistemaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsEarDiarioSubsistemaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsEarDiarioSubsistemaRepository extends JpaRepository<OnsEarDiarioSubsistemaEntity, Long> {}
