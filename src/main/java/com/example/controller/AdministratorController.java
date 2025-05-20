package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    @GetMapping("/")
    public String toLogin(LoginForm form, Model model){
        model.addAttribute("mailAddress", form.getMailAddress());
        model.addAttribute("password", form.getPassword());

        return "administrator/login";
    }

    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form, Model model){
        model.addAttribute("name" , form.getName());
        model.addAttribute("mailAddress", form.getMailAddress());
        model.addAttribute("password", form.getPassword());

        return "administrator/insert.html";
    }

    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form, Model model){
        Administrator administrator = new Administrator();
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        administratorService.insert(administrator);

        model.addAttribute("mailAddress", form.getMailAddress());
        model.addAttribute("password", form.getPassword());
        model.addAttribute("administrator", administrator);

        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(LoginForm form, Model model){
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        if(administrator == null){
            model.addAttribute("message", "メールアドレスまたはパスワードが不正です。");
            return "/";
        }

        session.setAttribute("administratorName", administrator.getName());

        return "redirect:/employee/showList";
    }

    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }

}
