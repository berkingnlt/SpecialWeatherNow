package net.javaguides.emsbackend.service;


import java.util.List;

//import org.hibernate.mapping.List;

import net.javaguides.emsbackend.dto.EmployeeDto;
//import net.javaguides.emsbackend.entity.Employee;

public interface EmployeeService {
   EmployeeDto createEmployee(EmployeeDto employeeDto);
    
   EmployeeDto getEmployeeById(Long employeeId);

   EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee);

   void deleteEmployee(Long employeeId);

   EmployeeDto save(EmployeeDto employeeDto);

   List<EmployeeDto> listAll();

}
