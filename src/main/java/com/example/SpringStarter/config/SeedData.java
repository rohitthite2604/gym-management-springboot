package com.example.SpringStarter.config;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.SpringStarter.models.Account;
import com.example.SpringStarter.models.Authority;
import com.example.SpringStarter.services.AccountService;
import com.example.SpringStarter.services.AuthorityService;
import com.example.SpringStarter.util.constants.Privillages;
import com.example.SpringStarter.util.constants.Roles;

@Component
public class SeedData implements CommandLineRunner{

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for(Privillages auth: Privillages.values()){
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }
        
        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        account01.setEmail("rohitthite@gmail.com");
        account01.setPassword("pass987");
        account01.setName("Rohit Thite");
        account01.setAge(20);
        account01.setDate_of_birth(LocalDate.parse("2003-04-26"));
        account01.setGender("Male");

        account02.setEmail("admin@admin.com");
        account02.setPassword("pass987");
        account02.setName("Admin");
        account02.setRole(Roles.ADMIN.getRole());
        account02.setAge(20);
        account02.setDate_of_birth(LocalDate.parse("2003-04-26"));
        account02.setGender("Female");

        account03.setEmail("editor@editor.com");
        account03.setPassword("pass987");
        account03.setName("Editor");
        account03.setRole(Roles.EDITOR.getRole());
        account03.setAge(55);
        account03.setDate_of_birth(LocalDate.parse("1972-01-13"));
        account03.setGender("Male");

        account04.setEmail("super_editor@editor.com");
        account04.setPassword("pass987");
        account04.setName("Editor");
        account04.setRole(Roles.EDITOR.getRole());
        account04.setAge(44);
        account04.setDate_of_birth(LocalDate.parse("1980-04-26"));
        account04.setGender("Female");
        Set<Authority> authorities = new HashSet<>();
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        account04.setAuthorities(authorities);

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);
    }
    
}
