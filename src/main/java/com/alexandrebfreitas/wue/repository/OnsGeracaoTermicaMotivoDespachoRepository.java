package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.OnsGeracaoTermicaMotivoDespachoEntity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OnsGeracaoTermicaMotivoDespachoEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OnsGeracaoTermicaMotivoDespachoRepository extends JpaRepository<OnsGeracaoTermicaMotivoDespachoEntity, Long> {}
