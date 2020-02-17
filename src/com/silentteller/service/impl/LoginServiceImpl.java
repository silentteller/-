package com.silentteller.service.impl;

import com.silentteller.entity.Reader;
import com.silentteller.repository.AdminRepository;
import com.silentteller.repository.ReaderRepository;
import com.silentteller.repository.impl.AdminRepositoryImpl;
import com.silentteller.repository.impl.ReaderRepositoryImpl;
import com.silentteller.service.LoginService;

public class LoginServiceImpl implements LoginService {

    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();
    @Override
    public Object login(String username, String password, String type) {
        Object object = null;
        switch (type){
            case "reader":
                object = readerRepository.login(username, password);
                break;
            case "admin":
                object = adminRepository.login(username, password);
                break;
        }
        //Reader reader = readerRepository.login(username, password);
        return object;
    }
}
