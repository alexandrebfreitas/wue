package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaDiariaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCargaEnergiaDiariaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCargaEnergiaDiariaRepository extends JpaRepository<OnsCargaEnergiaDiariaEntity, Long> {}
