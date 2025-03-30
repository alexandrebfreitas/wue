package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaProgramadaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCargaEnergiaProgramadaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCargaEnergiaProgramadaRepository extends JpaRepository<OnsCargaEnergiaProgramadaEntity, Long> {}
