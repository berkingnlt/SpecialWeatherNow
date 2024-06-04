package net.javaguides.emsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.emsbackend.entity.Employee;

public interface EmployeeRepository extends JpaRepository <Employee,Long>{
    Employee findByEmailAndPassword(String email, String password);
}
