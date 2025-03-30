package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsGeracaoUsinaEmBaseHorariaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsGeracaoUsinaEmBaseHorariaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsGeracaoUsinaEmBaseHorariaRepository extends JpaRepository<OnsGeracaoUsinaEmBaseHorariaEntity, Long> {}
