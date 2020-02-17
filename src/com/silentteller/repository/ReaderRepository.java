package com.silentteller.repository;

import com.silentteller.entity.Reader;

public interface ReaderRepository {
    public Reader login(String username, String password);
}
