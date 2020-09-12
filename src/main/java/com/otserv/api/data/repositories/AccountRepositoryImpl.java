package com.otserv.api.data.repositories;

import com.otserv.api.core.domain.Account;
import com.otserv.api.core.repositories.AccountRepository;
import com.otserv.api.data.entities.AccountEntity;
import com.otserv.api.data.repositories.jpa.JpaAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private JpaAccountRepository jpaAccountRepository;

    public AccountRepositoryImpl(JpaAccountRepository jpaAccountRepository) {
        this.jpaAccountRepository = jpaAccountRepository;
    }

    @Override
    public Optional<Account> findByName(String name) {
        return this.jpaAccountRepository
                .findByName(name)
                .map(AccountEntity::to);
    }

    @Override
    public boolean existsByNameOrEmail(String name, String email) {
        return this.jpaAccountRepository.existsByNameOrEmail(name, email);
    }

    @Override
    public Account save(Account account) {
        return AccountEntity.to(
                this.jpaAccountRepository.save(AccountEntity.from(account))
        );
    }

    @Override
    public Optional<Account> findById(Long id) {
        return this.jpaAccountRepository
                .findById(id)
                .map(AccountEntity::to);
    }
}
