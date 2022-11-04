package com.niit.AdminService.service;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Task;
import com.niit.AdminService.exception.AdminNotFoundException;
import com.niit.AdminService.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService{
    private AdminRepository adminRepository;

    @Autowired
    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


    @Override
    public Admin findByEmailAndPassword(String email, String password) throws AdminNotFoundException {
        Admin admin = adminRepository.findByEmailAndPassword(email,password);
        if (admin ==null)
        {
            throw new AdminNotFoundException();
        }
        return admin;
    }

    @Override
    public Admin addToTask(String email, Task task) {
        Admin admin = adminRepository.findById(email).get();
        if(admin.getTask() == null) {
            admin.setTask(Arrays.asList(task));
        }
        else {
            admin.getTask().add(task);
        }
        return adminRepository.save(admin);
    }

    @Override
    public Boolean deleteFromTask(String email, long taskId) {
        Admin admin = adminRepository.findById(email).get();
        admin.getTask().removeIf(x -> Objects.equals(x.getTaskId(), taskId));
        adminRepository.save(admin);
        return true;
    }

    @Override
    public List<Task> getFromTask(String email) {
        return adminRepository.findById(email).get().getTask();
    }
}
