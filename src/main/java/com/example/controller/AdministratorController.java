package com.example.controller;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者情報を操作するコントローラ.
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private HttpSession session;

    /**
     * 管理者登録画面の表示.
     * @param form フォーム
     * @return 管理者登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form, Model model){
        return "administrator/insert.html";
    }

    /**
     * 管理者登録処理
     * @param form フォーム
     * @param model モデル
     * @return ログイン画面
     */
    @PostMapping("/insert")
    public String insert(@Validated InsertAdministratorForm form, BindingResult result, Model model){
        if (result.hasErrors()){
            return toInsert(form, model);
        }
        Administrator administrator = new Administrator();
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        administratorService.insert(administrator);

        return "redirect:/";
    }

    /**
     * ログイン画面を表示する.
     *
     * @param form ログイン情報を保持するフォーム
     * @return ログイン画面
     */
    @GetMapping("/")
    public String toLogin(LoginForm form){
        return "administrator/login";
    }

    /**
     * ログイン処理
     * @param form フォーム
     * @param model モデル
     * @return ログインに成功→雇用者リスト、
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model){
        Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        if(administrator == null){
            model.addAttribute("result", false);
            return "/";
        }

        session.setAttribute("administratorName", administrator.getName());

        return "redirect:/employee/showList";
    }

    /**
     * ログアウト機能.
     * @return ログイン画面
     */
    @GetMapping("/logout")
    public String logout(){
        session.invalidate();
        return "redirect:/";
    }

}
