package net.javaguides.emsbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

//import java.security.PublicKey;
//import java.util.stream.Collector;

//import org.hibernate.mapping.List;
//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.emsbackend.dto.EmployeeDto;
import net.javaguides.emsbackend.mapper.EmployeeMapper;
import net.javaguides.emsbackend.repository.EmployeeRepository;
import net.javaguides.emsbackend.service.EmployeeService;
import net.javaguides.emsbackend.entity.Employee;
import net.javaguides.emsbackend.exception.ResourceNotFoundExpection;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository EmployeeRepository;
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedemployee = EmployeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedemployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = EmployeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundExpection("Employee is not exists with given id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {
        Employee employee = EmployeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundExpection("Employee is not existx with given id: " + employeeId)
        );
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());
        employee.setEmail(updateEmployee.getEmail());
        employee.setPassword(updateEmployee.getPassword());
        employee.setPhone(updateEmployee.getPhone());
        employee.setDate(updateEmployee.getDate());

        Employee updateEmployeeObj = EmployeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = EmployeeRepository.findById(employeeId).orElseThrow(
            () -> new ResourceNotFoundExpection("Employee is not existx with given id: " + employeeId)
        );
        EmployeeRepository.deleteById(employeeId);
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = EmployeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> listAll() {
        List<Employee> employees = EmployeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee e : employees) {
            employeeDtos.add(EmployeeMapper.mapToEmployeeDto(e));
        }
        return employeeDtos;
    }

   
    
    
   
}
