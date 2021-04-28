/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

/**
 *
 * @author Red
 */
import com.example.demo.entities.Education;
import java.util.ArrayList;
import java.util.List;

public interface EducationService {

    ArrayList getCountLevelEducation();

    List<Education> findByStaffId(int staff_id);

    List<Education> findAll();

    List<Education> findUndo();

    Education findOne(int id);

    void editEducaion(Education education);

    void createEducaion(Education education);

    boolean deleteEducation(int id);

    void setDelEducaion(int id, boolean del);
    
    List<Education> checkEducation(String school, String field_of_study, int staff_id, int id);
}
