package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsIntercambiosEntreSubsistemasEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsIntercambiosEntreSubsistemasEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsIntercambiosEntreSubsistemasRepository extends JpaRepository<OnsIntercambiosEntreSubsistemasEntity, Long> {}
