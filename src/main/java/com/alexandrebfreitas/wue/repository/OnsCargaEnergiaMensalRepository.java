package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaMensalEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCargaEnergiaMensalEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCargaEnergiaMensalRepository extends JpaRepository<OnsCargaEnergiaMensalEntity, Long> {}
