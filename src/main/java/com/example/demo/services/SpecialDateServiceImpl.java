/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.SpecialDate;
import com.example.demo.repositories.SpecialDateRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Red
 */
@Service
public class SpecialDateServiceImpl implements SpecialDateService{
    @Autowired
    private SpecialDateRepository repository;

    @Autowired
    EntityManagerFactory emf;
    
    @Override
    public List<SpecialDate> getAllSpecialDate(Date special_date_from) {
        return repository.findAll(special_date_from);
    }
    
    @Override
    public void addSpecialDate(SpecialDate specialDate) {
        repository.save(specialDate);
    }

    @Override
    public void deleteSpecialDate(int id) {
        repository.deleteById(id);
    }
    
    @Override
    public SpecialDate findSpecialDate(int id) {
        return repository.findSpecialDate(id);
    }

    @Override
    public void updateSpecialDate(Date special_date_from, Date special_date_to, String note, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE SpecialDate SET day_special_from = ?1, day_special_to = ?2, note = ?3 WHERE id = ?4")
                .setParameter(1, special_date_from)
                .setParameter(2, special_date_to)
                .setParameter(3, note)
                .setParameter(4, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void updateStaffOt(String string_staff_ot, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE SpecialDate SET staff_ot = ?1 WHERE id = ?2")
                .setParameter(1, string_staff_ot)
                .setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<SpecialDate> checkSpecialDate(String day_check) {
        try {
            Date day = new SimpleDateFormat("yyyy-MM-dd").parse(day_check);
            return repository.checkSpecialDate(day);
        } catch (ParseException ex) {
            Logger.getLogger(SpecialDateServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Map<String, Object>> getRequestOT(Date special_date, int staff_request, int department_request) {
        return repository.GetRequestOT(special_date, staff_request, department_request);
    }
    
    @Override
    public void approveOt(int is_approved, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE SpecialDate SET is_approved = ?1 WHERE id = ?2")
                .setParameter(1, is_approved)
                .setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Map<String, Object> getDetailOT(int special_date_id) {
        return repository.GetDetailOT(special_date_id);
    }

    @Override
    public ArrayList<Map<String, Object>> GetListSpecialDate(Date y_m) {
        return repository.GetListSpecialDate(y_m);
    }
    
}
