package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> showList(){
        return employeeRepository.findAll();
    }

    public Employee showDetail(Integer id){
        return employeeRepository.findById(id);
    }

    public void update(Employee employee){
        employeeRepository.update(employee);
    }
}
