package com.tenex.rest.springboot4.controller;


import com.tenex.rest.springboot4.exception.BadRequestException;
import com.tenex.rest.springboot4.exception.ResourceNotFoundException;
import com.tenex.rest.springboot4.model.Employee;
import com.tenex.rest.springboot4.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

        @GetMapping("/employees")
        public List<Employee> getAllEmployees(){
            return  employeeRepository.findAll();

        };



    @PostMapping("/employees")
    public ResponseEntity<String> createResource(@RequestBody Employee employee) {


        if ((employee.getFirstName() == null && employee.getLastName() == null) && employee.getLastName() == null){
            throw new BadRequestException("Failed to create employee");


        }else {
            Employee savedEmployee = employeeRepository.save(employee);

            return ResponseEntity.ok("Resource created successfully");

        }
    }



    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee of id "+id +" not found"));

      return ResponseEntity.ok(employee);
    };



    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){


            Optional<Employee> employee = employeeRepository.findById(id);
            if(employee.isPresent()){
                employeeRepository.deleteById(id);
                return  ResponseEntity.ok("Employee of id " + id + " has been deleted");


            }else{
                throw new ResourceNotFoundException("Error:  Employee of id " + id + "could not be found");
            }
            //How do I trwo error in spring boot , in what scenarios do I throw errors and how , using what

    }


//    @PutMapping("/employees/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
//
//
//        Optional<Employee> employeeOld = employeeRepository.findById(id);
//        if(employeeOld.isPresent()){
//            Employee oldemployee = employeeOld.get();
//            oldemployee.setFirstName(employee.getFirstName());
//            oldemployee.setLastName(employee.getLastName());
//            oldemployee.setEmailId(employee.getEmailId());
//
//            Employee updatedEmployee = employeeRepository.save(oldemployee);
//            return  ResponseEntity.ok(updatedEmployee);
//
//
//        }else{
//            throw new ResourceNotFoundException("Error:  Employee of id " + id + "could not be found");
//        }
//
//    }


        @PutMapping("/employees/{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedFields) {
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);

            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                if (updatedFields.getFirstName() != null) {
                    employee.setFirstName(updatedFields.getFirstName());
                }
                if (updatedFields.getLastName() != null) {
                    employee.setLastName(updatedFields.getLastName());
                }
                if (updatedFields.getEmailId() != null) {
                    employee.setEmailId(updatedFields.getEmailId());
                }

                Employee savedEmployee = employeeRepository.save(employee);
                return ResponseEntity.ok(savedEmployee);
            } else {
                return ResponseEntity.notFound().build();
            }
        }






};

