package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCargaEnergiaVerificadaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCargaEnergiaVerificadaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCargaEnergiaVerificadaRepository extends JpaRepository<OnsCargaEnergiaVerificadaEntity, Long> {}
