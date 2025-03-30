package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsBalancoEnergiaDessemEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsBalancoEnergiaDessemEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsBalancoEnergiaDessemRepository extends JpaRepository<OnsBalancoEnergiaDessemEntity, Long> {}
