package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsOfertasPrecoParaImportacaoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsOfertasPrecoParaImportacaoEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsOfertasPrecoParaImportacaoRepository extends JpaRepository<OnsOfertasPrecoParaImportacaoEntity, Long> {}
