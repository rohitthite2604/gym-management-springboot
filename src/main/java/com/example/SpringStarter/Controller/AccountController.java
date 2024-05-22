package com.example.SpringStarter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.SpringStarter.models.Account;
import com.example.SpringStarter.services.AccountService;

import jakarta.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/register")
    public String register(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "/register";
    }

    @PostMapping("/register")
    public String register_user(@Valid @ModelAttribute Account account, BindingResult result){
        if(result.hasErrors()){
            return "/register";
        }

        accountService.save(account);
        return"redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model){
        return"/login";
    }

    @GetMapping("/profile")
    public String profile(Model model){
        return"/profile";
    }

    @GetMapping("/test")
    public String test(Model model){
        return"/test";
    }
    
}
