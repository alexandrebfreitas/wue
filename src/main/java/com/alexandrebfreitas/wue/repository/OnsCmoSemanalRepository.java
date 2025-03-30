package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCmoSemanalEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCmoSemanalEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCmoSemanalRepository extends JpaRepository<OnsCmoSemanalEntity, Long> {}
