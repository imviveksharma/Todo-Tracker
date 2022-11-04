package com.niit.AdminService.service;

import com.niit.AdminService.domain.Admin;
import com.niit.AdminService.domain.Task;
import com.niit.AdminService.exception.AdminNotFoundException;

import java.util.List;

public interface AdminService {
    Admin saveAdmin(Admin admin);

    Admin findByEmailAndPassword(String email, String password) throws AdminNotFoundException;

    Admin addToTask(String email, Task task);

    Boolean deleteFromTask(String email, long taskId);

    List<Task> getFromTask(String email);
}
