/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.LeaveOther;
import com.example.demo.repositories.LeaveOtherRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public class LeaveOtherImpl implements LeaveOtherService{
    @Autowired
    private LeaveOtherRepository repository;
    
    @Autowired
    EntityManagerFactory emf;
    
    @Override
    public void add(LeaveOther leaveOther) {
        repository.save(leaveOther);
    }

    @Override
    public List<LeaveOther> list(int staff_id, Date month_get) {
        return repository.getListLeaveOther(staff_id, month_get);
    }

    @Override
    public ArrayList<Map<String, Object>> GetLeaveOtherFromTo(String from_date, String to_date) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            return repository.GetLeaveOtherFromTo(from, to);
        } catch (ParseException ex) {
            Logger.getLogger(LeaveOtherImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Map<String, Object>> GetStaffApproveLeaveOther(String day_time_leave, Integer department, Integer is_manager, Integer staff_id) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(day_time_leave);
            return repository.GetStaffApproveLeaveOther(time, department, is_manager, staff_id);
        } catch (ParseException ex) {
            Logger.getLogger(TimeLeaveImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getDetailOtherLeave(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT t.id, t.fromDate, t.toDate, t.typeLeave, t.note, t.isApproved, s.firstname, s.lastname, s.code, s.isManager, d.name, d.nameVn, t.image "
                + "FROM LeaveOther AS t "
                + "INNER JOIN Staff AS s ON t.staffId = s.id "
                + "INNER JOIN Department AS d ON s.department = d.id "
                + "WHERE t.id = ?1").setParameter(1, id);
        List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) query.getResultList();
        em.close();
        return list;
    }
    
    @Override
    public void approveLeaveOther(String is_approved, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE LeaveOther SET is_approved = ?1 WHERE id = ?2")
                .setParameter(1, is_approved)
                .setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void adminApproveLeaveOther(String is_approved, int id, Date day_approve) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE LeaveOther SET is_approved = ?1, day_approved =?2 WHERE id = ?3")
                .setParameter(1, is_approved)
                .setParameter(2, day_approve)
                .setParameter(3, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Optional<LeaveOther> findLeaveOther(int id) {
        return repository.findById(id);
    }
    
    @Override
    public boolean deleteLeaveOther(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

    @Override
    public boolean checkDuplicate(int staff_id, String from_date, String to_date) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            LeaveOther check_leave_other = repository.checkDuplicate(staff_id, from, to);
            if(check_leave_other == null) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
     @Override
    public boolean checkDuplicateUpdate(int staff_id, String from_date, String to_date, int id) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            LeaveOther check_leave_other = repository.checkDuplicateUpdate(staff_id, from, to, id);
            if(check_leave_other == null) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
