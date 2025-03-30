package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseDiariaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsDadosHidraulicosReservatorioBaseDiariaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsDadosHidraulicosReservatorioBaseDiariaRepository
    extends JpaRepository<OnsDadosHidraulicosReservatorioBaseDiariaEntity, Long> {}
