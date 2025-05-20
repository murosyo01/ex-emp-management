package com.example.controller;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private HttpSession session;

    @GetMapping("/showList")
    public String showList(Model model){
        List<Employee> employeeList = employeeService.showList();

        model.addAttribute("employeeList", employeeList);

        return "employee/list";
    }

    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.parseInt(id));

        model.addAttribute("employee", employee);

//        model.addAttribute("name", form.getName());
//        model.addAttribute("image", form.getImage());
//        model.addAttribute("gender", form.getGender());
//        model.addAttribute("mailAddress", form.getMailAddress());
//        model.addAttribute("zipCode", form.getZipCode());
//        model.addAttribute("address", form.getAddress());
//        model.addAttribute("telephone", form.getTelephone());
//        model.addAttribute("salary", form.getSalary());
//        model.addAttribute("characteristics", form.getCharacteristics());
//        model.addAttribute("dependentsCount", form.getDependentsCount());

        return "employee/detail";
    }

}
