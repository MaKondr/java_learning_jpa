package org.example.springjpa.services;

import org.example.springjpa.models.Department;
import org.example.springjpa.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(System.out::println);
        return departments;
    }
    public Department findById(int id) {
        Optional<Department> foundDepartment = departmentRepository.findById(id);
        foundDepartment.ifPresent(System.out::println);
        return foundDepartment.orElse(null);
    }
    @Transactional
    public void save(Department department) {

        try {
            departmentRepository.save(department);
        } catch (Exception e) {
            System.out.println("BAD REQUEST");
        }
    }
    @Transactional
    public void delete(int id) {
        departmentRepository.deleteById(id);
    }


}
