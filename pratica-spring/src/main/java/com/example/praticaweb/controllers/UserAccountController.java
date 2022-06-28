package com.example.praticaweb.controllers;

import com.example.praticaweb.entities.UserAccount;
import com.example.praticaweb.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAccountController {
    @Autowired
    private UserAccountService service;

    @GetMapping
    public List<UserAccount> getAllUserAccounts() throws Exception{
        try {
            return service.getAllUserAccounts();
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }

    @PostMapping
    public UserAccount addUserAccount(@RequestBody UserAccount user) throws Exception{
        try {
            return service.addUserAccount(user);
        }
        catch (Exception e){
            throw new Exception(e);
        }
    }
}