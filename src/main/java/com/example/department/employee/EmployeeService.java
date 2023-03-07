package com.example.department.employee;

import com.example.department.department.Department;
import com.example.department.department.DepartmentRepository;
import com.example.department.services.ministry.Ministry;
import com.example.department.services.ministry.MinistryRestClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private MinistryRestClient ministryRestClient;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           MinistryRestClient ministryRestClient) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.ministryRestClient = ministryRestClient;
    }

    public boolean register(EmployeeDto employeeDto){
        Department department = departmentRepository.findById(employeeDto.departmentId)
                .orElseThrow(()->new RuntimeException("No department found with the id: "+employeeDto.departmentId));

        try {
            Ministry ministry = ministryRestClient.findMinistryByUUID(employeeDto.ministryUUID);

            Employee employee = new Employee();
            employee.setEmail(employeeDto.email);
            employee.setFirstName(employeeDto.firstName);
            employee.setLastName(employeeDto.lastName);
            employee.setMinistryUUID(ministry.getUuid());
            employee.setPhone(employeeDto.phone);
            employee.setDepartment(department);

            employeeRepository.save(employee);
            return employee.getId() != null;

        }catch (Exception e){
            return false;
        }

    }

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id).
                orElseThrow(()->new RuntimeException("No employee found with the id: "+id));
    }
}
