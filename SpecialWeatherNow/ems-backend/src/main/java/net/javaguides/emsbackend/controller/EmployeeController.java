package net.javaguides.emsbackend.controller;

import org.springframework.web.bind.annotation.RestController;

//import jakarta.servlet.http.HttpServletResponse;
import net.javaguides.emsbackend.dto.EmployeeDto;
//import net.javaguides.emsbackend.entity.Employee;
import net.javaguides.emsbackend.service.EmployeeService;

//import org.apache.catalina.connector.Response;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;







@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> createemployee(@RequestBody EmployeeDto employeeDto){
       EmployeeDto savedemployee = employeeService.createEmployee(employeeDto);
    return new ResponseEntity<>(savedemployee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> listEmployee() {
        List<EmployeeDto> employeeDtos = employeeService.listAll();

        return new ResponseEntity<List<EmployeeDto>>(employeeDtos, HttpStatusCode.valueOf(200));
    }
    

    @GetMapping ("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
    
    @PutMapping ("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updateEmployee) {
       EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployee);
       return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping ("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfuly!. ");
    }
}