package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsDadosHidraulicosReservatorioBaseHorariaEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsDadosHidraulicosReservatorioBaseHorariaEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsDadosHidraulicosReservatorioBaseHorariaRepository
    extends JpaRepository<OnsDadosHidraulicosReservatorioBaseHorariaEntity, Long> {}
