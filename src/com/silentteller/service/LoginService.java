package com.silentteller.service;

import com.silentteller.entity.Reader;

public interface LoginService {
    public Object login(String username, String password, String type);
}
