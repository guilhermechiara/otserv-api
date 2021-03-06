package com.otserv.api.core.repositories;


import com.otserv.api.core.domain.Account;

import java.util.Optional;

public interface AccountRepository {
    public Optional<Account> findByName(String name);
    public Optional<Account> findById(Long id);

    public boolean existsByNameOrEmail(String name, String email);

    public Account save(Account account);
}
