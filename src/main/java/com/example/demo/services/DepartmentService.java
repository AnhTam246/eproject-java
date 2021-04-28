/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Department;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface DepartmentService {
    
    List<Department> findAll();
    List<Department> findUndo();

    Department findOne(int id);

    void editDepartment(Department department);

    void createDepartment(Department department);
    
    void deleteDepartment(Department department);
    
    boolean deleteDepartment1(int id);
    
    void setDelDepartment(int id, boolean del);
    
    List<Department> checkDepartment(int id, String name, String name_vn);
}
