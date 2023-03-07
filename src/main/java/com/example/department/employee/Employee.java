package com.example.department.employee;

import com.example.department.department.Department;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String phone;

    @Column(name = "ministry_uuid")
    private String ministryUUID;

    private String role = "ROLE_USER";

    @ManyToOne
    @JsonManagedReference
    private Department department;
}
