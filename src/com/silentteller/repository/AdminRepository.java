package com.silentteller.repository;

import com.silentteller.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}
