/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

/**
 *
 * @author nguyenngocanhtam
 */
import com.example.demo.entities.Staff;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface StaffService {

    List<Staff> findAll();

    List<Staff> findUndo();

    Staff findOne(int id);

    List<Staff> findStaffDepartment();

    ArrayList<Map<String, Object>> findOneStaffDepartment(int id);

    List<Staff> search(String firstname, String lastname);

    void editStaff(Staff staff);

    Staff save(Staff staff);

    void createStaff(Staff staff);

    void deleteStaff(int id);

    ArrayList getStaffMonth();

    ArrayList getStaffOffDateMonth();

    ArrayList getTN();

    void updateDateOfLeave();

    void resetDateOfLeave();

    Map<String, Object> getProfile(int staff_id);

    void setDelStaff(int id, boolean status);

    void updateDepartment(int department, int id);

    boolean checkOldPass(int id, String password);

    void updatePassword(int id, String new_pass);

    List<Staff> findStaffDepartment(int department);
    
    boolean checkEmail(String email, int id);
    
    boolean checkIdNumber(String id_number, int id);
}
