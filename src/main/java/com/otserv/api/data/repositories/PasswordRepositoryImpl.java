package com.otserv.api.data.repositories;

import com.google.common.hash.Hashing;
import com.otserv.api.core.repositories.PasswordRepository;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;

@Repository
public class PasswordRepositoryImpl implements PasswordRepository {
    @Override
    public String encode(String password) {
        /* Todo: sha1 vs sha256? */
        return Hashing.sha1()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();
    }
}
