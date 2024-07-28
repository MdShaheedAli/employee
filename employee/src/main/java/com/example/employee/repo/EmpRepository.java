package com.example.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee.dto.Employee;

public interface EmpRepository extends JpaRepository<Employee,Long>{

}
