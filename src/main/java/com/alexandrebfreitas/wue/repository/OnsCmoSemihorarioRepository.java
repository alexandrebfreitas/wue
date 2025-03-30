package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCmoSemihorarioEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCmoSemihorarioEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCmoSemihorarioRepository extends JpaRepository<OnsCmoSemihorarioEntity, Long> {}
