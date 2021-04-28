/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Department;
import com.example.demo.repositories.DepartmentRepository;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<Department> findAll() {
        return (List<Department>) repository.ListAll();
    }
    
    @Override
    public List<Department> findUndo() {
        return (List<Department>) repository.ListUndo();
    }


    @Override
    public Department findOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public void editDepartment(Department department) {
        repository.save(department);
    }

    @Override
    public void createDepartment(Department department) {
        repository.save(department);
    }

    @Override
    public void deleteDepartment(Department department) {
        repository.delete(department);
    }

    @Override
    public boolean deleteDepartment1(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

    @Override
    public void setDelDepartment(int id, boolean del) {
        repository.setDelDepartment(id, del);
    }

    @Override
    public List<Department> checkDepartment(int id, String name, String name_vn) {
        return repository.checkDepartment(id, name, name_vn);
    }

    
}
