package com.example.homeWork21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/max-salary")
    public ResponseEntity<Employee> getMaxSalaryEmployee(@RequestParam String departmentId) {
        Employee employee = departmentService.getEmployeeWithMaxSalary(departmentId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/min-salary")
    public ResponseEntity<Employee> getMinSalaryEmployee(@RequestParam String departmentId) {
        Employee employee = departmentService.getEmployeeWithMinSalary(departmentId);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestParam(required = false) String departmentId) {
        if (departmentId != null) {
            List<Employee> employees = departmentService.getAllEmployeesByDepartment(departmentId);
            return ResponseEntity.ok(employees);
        } else {
            Map<String, List<Employee>> employeesGrouped = departmentService.getAllEmployeesGroupedByDepartment();
            return ResponseEntity.ok(employeesGrouped);
        }
    }
}