package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/administrator")
public class AdministatorController {

    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "login";
    }

    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form, Model model){
        Administrator administrator = new Administrator();
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        model.addAttribute("mailAddress", form.getMailAddress());
        model.addAttribute("password", form.getPassword());
//        model.addAttribute("administrator", administrator);

        return "redirect:/administrator/";
    }

}
