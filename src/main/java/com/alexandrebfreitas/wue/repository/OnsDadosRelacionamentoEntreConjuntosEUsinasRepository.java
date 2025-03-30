package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsDadosRelacionamentoEntreConjuntosEUsinasEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsDadosRelacionamentoEntreConjuntosEUsinasEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsDadosRelacionamentoEntreConjuntosEUsinasRepository
    extends JpaRepository<OnsDadosRelacionamentoEntreConjuntosEUsinasEntity, Long> {}
