/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Transfer;
import com.example.demo.repositories.TransferRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public class TransferImpl implements TransferService{
    @Autowired
    private TransferRepository repository;
    
    @Autowired
    EntityManagerFactory emf;
    
    @Override
    public ArrayList<Map<String, Object>> getListTransfer(Date month_get, int department) {
        return repository.getListTransfer(month_get, department);
    }

    @Override
    public Transfer createTransfer(Transfer transfer) {
        return repository.save(transfer);
    }
    
    @Override
    public void deleteTransfer(int id) {
        repository.deleteById(id);
    }
    
    @Override
    public Optional<Transfer> detailTransfer(int id) {
        return repository.findById(id);
    }
    
    @Override
    public void updateTransfer(int new_department, String note,int hr_approved,double new_salary, String note_manager, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Transfer SET new_department = ?1, note = ?2, hr_approved =?3, new_salary =?4, note_manager =?5 WHERE id = ?6")
                .setParameter(1, new_department)
                .setParameter(2, note)
                .setParameter(3, hr_approved)
                .setParameter(4, new_salary)
                .setParameter(5, note_manager)
                .setParameter(6, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Transfer getTransferCheck(int staff_id) {
        return repository.getTransferCheck(staff_id);
    }
    
    @Override
    public void oldManagerApprove(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Transfer SET old_manager_approved = 1 WHERE id = ?1")
                .setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
    @Override
    public void newManagerApprove(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Transfer SET new_manager_approved = 1 WHERE id = ?1")
                .setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void managerApprove(int id) {
         EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Transfer SET manager_approved = 1, update_at=CURDATE() WHERE id = ?1")
                .setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void setDelTransfer(int id, boolean del) {
       EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Transfer SET del = 1 WHERE id = ?1")
                .setParameter(1, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

  
    
    
  
}
