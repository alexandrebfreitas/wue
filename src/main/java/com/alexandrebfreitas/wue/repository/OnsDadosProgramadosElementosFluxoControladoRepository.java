package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsDadosProgramadosElementosFluxoControladoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsDadosProgramadosElementosFluxoControladoEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsDadosProgramadosElementosFluxoControladoRepository
    extends JpaRepository<OnsDadosProgramadosElementosFluxoControladoEntity, Long> {}
