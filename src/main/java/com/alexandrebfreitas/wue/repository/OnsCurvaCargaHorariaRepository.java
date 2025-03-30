package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCurvaCargaHorariaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCurvaCargaHorariaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCurvaCargaHorariaRepository extends JpaRepository<OnsCurvaCargaHorariaEntity, Long> {}
