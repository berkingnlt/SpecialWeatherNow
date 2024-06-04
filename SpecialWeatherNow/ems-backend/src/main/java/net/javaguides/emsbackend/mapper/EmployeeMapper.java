package net.javaguides.emsbackend.mapper;

import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.entity.Employee;

public class EmployeeMapper {
public static EmployeeDto mapToEmployeeDto(Employee employee){
    return new EmployeeDto(
        employee.getId(),
        employee.getFirstName(),
        employee.getLastName(),
        employee.getEmail(),
        employee.getPassword(),
        employee.getPhone(),
        employee.getDate()
    );
}
public static Employee mapToEmployee(EmployeeDto employeeDto){
    return new Employee(
        employeeDto.getId(),
        employeeDto.getFirstName(),
        employeeDto.getLastName(),
        employeeDto.getEmail(),
        employeeDto.getPassword(),
        employeeDto.getPhone(),
        employeeDto.getDate()
    );
}
}
