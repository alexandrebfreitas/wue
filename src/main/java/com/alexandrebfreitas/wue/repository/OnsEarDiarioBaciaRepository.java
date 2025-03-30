package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsEarDiarioBaciaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsEarDiarioBaciaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsEarDiarioBaciaRepository extends JpaRepository<OnsEarDiarioBaciaEntity, Long> {}
