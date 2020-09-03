package com.otserv.api.data.repositories.jpa;

import com.otserv.api.data.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {
    public Optional<AccountEntity> findByName(String name);
    public Optional<AccountEntity> findById(Long id);

    public boolean existsByNameOrEmail(String name, String email);
}
