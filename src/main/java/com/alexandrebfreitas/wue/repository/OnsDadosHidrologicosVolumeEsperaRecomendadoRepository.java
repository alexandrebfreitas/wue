package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsDadosHidrologicosVolumeEsperaRecomendadoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsDadosHidrologicosVolumeEsperaRecomendadoEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsDadosHidrologicosVolumeEsperaRecomendadoRepository
    extends JpaRepository<OnsDadosHidrologicosVolumeEsperaRecomendadoEntity, Long> {}
