package org.example.springjpa.services;

import org.example.springjpa.models.Staff;
import org.example.springjpa.repositories.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
public class StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> findAll() {
        List<Staff> staffs = staffRepository.findAll();
        staffs.forEach(System.out::println);
        return staffs;
    }
    public Staff findById(int id) {
        Optional<Staff> foundStaff = staffRepository.findById(id);
        foundStaff.ifPresent(System.out::println);
        return foundStaff.orElse(null);
    }
    @Transactional
    public void save(Staff staff) {
        staffRepository.save(staff);
    }
    @Transactional
    public void delete(int id) {
        staffRepository.deleteById(id);
    }
}
