package com.example.service;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 雇用者リポジトリを操作するサービス
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 全雇用者を検索
     * @return
     */
    public List<Employee> showList(){
        return employeeRepository.findAll();
    }

    /**
     * 指定したIDをもつ雇用者を検索
     * @param id 雇用者ID
     * @return 雇用者オブジェクト
     */
    public Employee showDetail(Integer id){
        return employeeRepository.findById(id);
    }

    /**
     * 雇用者情報を更新する
     * @param employee 雇用者オブジェクト
     */
    public void update(Employee employee){
        employeeRepository.update(employee);
    }
}
