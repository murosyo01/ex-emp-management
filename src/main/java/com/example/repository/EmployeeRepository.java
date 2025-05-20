package com.example.repository;

import com.example.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * employeesテーブルを操作するリポジトリ.
 */
@Repository
public class EmployeeRepository {
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));

        return employee;
    };

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * 雇用者を全て検索する
     * @return 雇用者リスト
     */
    public List<Employee> findAll(){
        String sql = "SELECT * FROM employees ORDER BY hire_date DESC;";
        List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);

        if (employeeList.isEmpty()){
            return null;
        }
        return employeeList;
    }

    /**
     * IDを基に雇用者を検索する
     * @param id 雇用者ID
     * @return 雇用者オブジェクト
     */
    public Employee findById(Integer id){
        String sql = "SELECT * FROM employees WHERE id = :id;";
        SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

        return employee;
    }

    /**
     * 雇用者情報を更新する
     * @param employee 雇用者オブジェクト
     */
    public void update(Employee employee){
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

        String sql = "UPDATE employees SET name = :name, image = :image, gender = :gender, hire_date = :hireDate, mail_address = :mailAddress, zip_code = :zipCode, address = :address, telephone = :telephone, salary = :salary, characteristics = :characteristics, dependents_count = :dependentsCount WHERE id = :id;";

        template.update(sql, param);
    }

}
