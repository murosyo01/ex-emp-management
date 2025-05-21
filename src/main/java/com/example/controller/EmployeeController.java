package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * 雇用者に対する操作を指示するコントローラー.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession session;

    /**
     * 全ての雇用者を表示.
     * @param model モデル
     * @return 雇用者リスト
     */
    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();

        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    /**
     * 選択した雇用者の情報を表示.
     * @param id 雇用者ID
     * @param model モデル
     * @param form フォーム
     * @return idに紐づく雇用者のオブジェクト
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.parseInt(id));

        BeanUtils.copyProperties(employee, form);

        model.addAttribute("employee", employee);


        return "employee/detail";
    }

    /**
     * 雇用者IDを基に雇用者オブジェクトを更新.
     * @param form フォーム
     * @return 雇用者リスト
     */
    @PostMapping("/update")
    public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
        if (result.hasErrors()){
            model.addAttribute("employee", employeeService.showDetail(form.getId()));
            return showDetail(String.valueOf(form.getId()), model, form);
        }
        Employee employee = employeeService.showDetail(form.getId());
        employee.setName(form.getName());
        if(!form.getImage().isEmpty()){
            employee.setImage(form.getImage());
        }
        employee.setGender(form.getGender());
        employee.setHireDate(form.getHireDate());
        employee.setMailAddress(form.getMailAddress());
        employee.setZipCode(form.getZipCode());
        employee.setAddress(form.getAddress());
        employee.setTelephone(form.getTelephone());
        employee.setSalary(form.getSalary());
        employee.setCharacteristics(form.getCharacteristics());
        employee.setDependentsCount(form.getDependentsCount());
        employeeService.update(employee);

        return "redirect:/employee/showList";
    }
}
