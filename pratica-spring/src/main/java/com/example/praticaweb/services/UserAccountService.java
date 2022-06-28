package com.example.praticaweb.services;

import com.example.praticaweb.entities.UserAccount;
import com.example.praticaweb.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserAccount> getAllUserAccounts() throws Exception{
        try {
            return repository.findAll();
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional
    public Optional<UserAccount> findUserAccountByUsername(String userName){
        return repository.findUserAccountByUsername(userName);
    }

    @Transactional
    public UserAccount addUserAccount(UserAccount newUser) throws Exception{
        try {
            UserAccount user = new UserAccount();
            user.setUsername(newUser.getUsername());
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setFullname(newUser.getFullname());
            repository.saveAndFlush(user);
            return user;
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}