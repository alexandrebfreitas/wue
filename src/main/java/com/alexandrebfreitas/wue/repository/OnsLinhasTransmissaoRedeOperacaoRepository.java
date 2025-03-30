package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsLinhasTransmissaoRedeOperacaoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsLinhasTransmissaoRedeOperacaoEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsLinhasTransmissaoRedeOperacaoRepository extends JpaRepository<OnsLinhasTransmissaoRedeOperacaoEntity, Long> {}
