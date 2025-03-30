package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsEnaDiarioBaciaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsEnaDiarioBaciaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsEnaDiarioBaciaRepository extends JpaRepository<OnsEnaDiarioBaciaEntity, Long> {}
