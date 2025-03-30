package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsIntercambioSinComOutrosPaisesEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsIntercambioSinComOutrosPaisesEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsIntercambioSinComOutrosPaisesRepository extends JpaRepository<OnsIntercambioSinComOutrosPaisesEntity, Long> {}
