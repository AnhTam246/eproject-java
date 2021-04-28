/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Education;
import com.example.demo.repositories.EducationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Red
 */
@Service
public class EducationServiceImpl implements EducationService{
    @Autowired
    private EducationRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @Override
    public ArrayList getCountLevelEducation() {
        return repository.getCountLevelEducation();
    }

    @Override
    public List<Education> findByStaffId(int staff_id) {
        return repository.findByStaffId(staff_id);
    }

    @Override
    public List<Education> findAll() {
        return (List<Education>) repository.findAll();
    }

    @Override
    public List<Education> findUndo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Education findOne(int id) {
         Optional<Education> eduReponse = repository.findById(id);
        Education education = eduReponse.get();
        return education;
    }

    @Override
    public void editEducaion(Education education) {
        repository.save(education);
    }

    @Override
    public void createEducaion(Education education) {
        repository.save(education);
    }

    @Override
    public void setDelEducaion(int id, boolean del) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteEducation(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

    @Override
    public List<Education> checkEducation(String school, String field_of_study, int staff_id, int id) {
        return repository.checkEducation(school, field_of_study, staff_id, id);
    } 
}
