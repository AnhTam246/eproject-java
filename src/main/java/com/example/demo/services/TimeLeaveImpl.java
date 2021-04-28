/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.TimeLeave;
import com.example.demo.repositories.TimeLeaveRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
public class TimeLeaveImpl implements TimeLeaveService{
    @Autowired
    private TimeLeaveRepository repository;
    
    @Autowired
    EntityManagerFactory emf;
    
    @Override
    public void addTimeLeave(TimeLeave timeleave) {
        repository.save(timeleave);
    }
    
    @Override
    public TimeLeave findOne(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<TimeLeave> getListTimeLeave(int staff_id, String day_time_leave) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(day_time_leave);
            return repository.getListTimeLeave(staff_id, time);
        } catch (ParseException ex) {
            Logger.getLogger(TimeLeaveImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public TimeLeave checkAddTime(int staff_id, Date day_time_leave) {
        TimeLeave timeleave = repository.checkAddTime(staff_id, day_time_leave);
        return timeleave;
    }

    @Override
    public boolean deleteTime(int id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            
        }
        return false;
    }

    @Override
    public TimeLeave findTimeLeave(int id) {
        return repository.findTimeLeave(id);
    }

    @Override
    public void updateTimeLeave(Date day_time_leave, Date time, String note, int id, String image) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE TimeLeave SET day_time_leave = ?1, time = ?2, note = ?3, image=?4 WHERE id = ?5")
                .setParameter(1, day_time_leave)
                .setParameter(2, time)
                .setParameter(3, note)
                .setParameter(4, image)
                .setParameter(5, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void updateDayTimeLeaveOfStaff(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
          Query query = em.createQuery("UPDATE Staff SET day_of_leave = day_of_leave - 1 WHERE id = ?1")
                .setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void revertDayTimeLeaveOfStaff(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
          Query query = em.createQuery("UPDATE Staff SET day_of_leave = day_of_leave + 1 WHERE id = ?1")
                .setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArrayList getStaffApprove(String day_time_leave, Integer department, Integer is_manager, Integer staff_id) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(day_time_leave);
            return repository.GetStaffApprove(time, department, is_manager, staff_id);
        } catch (ParseException ex) {
            Logger.getLogger(TimeLeaveImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<HashMap<String, Object>> getDetailStaffApprove(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT t.id, t.dayTimeLeave, t.time, t.type, t.note, t.isApproved, s.firstname, s.lastname, s.code, s.isManager, d.name, d.nameVn, t.image "
                + "FROM TimeLeave AS t "
                + "INNER JOIN Staff AS s ON t.staffId = s.id "
                + "INNER JOIN Department AS d ON s.department = d.id "
                + "WHERE t.id = ?1").setParameter(1, id);
        List<HashMap<String, Object>> list = (List<HashMap<String, Object>>) query.getResultList();
        em.close();
        return list;
    }

    @Override
    public void approveTimeLeave(String is_approved, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE TimeLeave SET is_approved = ?1 WHERE id = ?2")
                .setParameter(1, is_approved)
                .setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void adminApproveTimeLeave(String is_approved, int id, Date day_approve) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE TimeLeave SET is_approved = ?1, day_approved =?2 WHERE id = ?3")
                .setParameter(1, is_approved)
                .setParameter(2, day_approve)
                .setParameter(3, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public ArrayList getAllStaffTime(String y_m) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(y_m);
            return repository.GetAllStaffTime(time);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public ArrayList getAllStaffTimeFromTo(String from_date, String to_date) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            return repository.GetAllStaffTimeFromTo(from, to);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Map<String, Object>> getTimeLeaveFromTo(String from_date, String to_date) {
         try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            return repository.getTimeLeaveFromTo(from, to);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void doneLeave(String from_date, String to_date) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            repository.doneLeave(from, to);
            
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Map<String, Object>> SummaryStaffTime(String y_m) {
       try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(y_m);
            return repository.SummaryStaffTime(time);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Map<String, Object>> SummaryTimeLeave(String y_m) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(y_m);
            return repository.SummaryTimeLeave(time);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<TimeLeave> checkListTimeLeave(int staff_id, String from_date, String to_date) {
        return repository.checkListTimeLeave(staff_id, from_date, to_date);
    }

    @Override
    public ArrayList<Map<String, Object>> GetDetailTimeLeaveAll(String month_get, int staff_id) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(month_get);
            return repository.GetDetailTimeLeaveAll(time, staff_id);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Map<String, Object>> GetAllDetailTimeLeave(String month_get) {
         try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(month_get);
            return repository.GetAllDetailTimeLeave(time);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
