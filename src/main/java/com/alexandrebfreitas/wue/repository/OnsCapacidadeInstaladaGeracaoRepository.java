package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsCapacidadeInstaladaGeracaoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsCapacidadeInstaladaGeracaoEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsCapacidadeInstaladaGeracaoRepository extends JpaRepository<OnsCapacidadeInstaladaGeracaoEntity, Long> {}
