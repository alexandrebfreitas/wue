package com.alexandrebfreitas.wue.repository;

import com.alexandrebfreitas.wue.domain.PersistentToken;
import com.alexandrebfreitas.wue.domain.UserEntity;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link PersistentToken} entity.
 */
public interface PersistentTokenRepository extends JpaRepository<PersistentToken, String> {
    List<PersistentToken> findByUser(UserEntity user);

    List<PersistentToken> findByTokenDateBefore(LocalDate localDate);
}
