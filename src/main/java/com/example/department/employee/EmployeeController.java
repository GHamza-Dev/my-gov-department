package com.example.department.employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<?> registerEmployee(@RequestBody EmployeeDto employee){
        if(employeeService.register(employee)) return ResponseEntity.ok("Employee saved successfully");
        return ResponseEntity.status(500).body("Ops something went wrong");
    }

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.ok(
                employeeService.getAll()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(
                employeeService.getById(id)
        );
    }
}
