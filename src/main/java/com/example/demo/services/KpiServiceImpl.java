/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Kpi;
import com.example.demo.entities.KpiDetail;
import com.example.demo.repositories.KpiDetailRepository;
import com.example.demo.repositories.KpiRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class KpiServiceImpl implements KpiService{
    @Autowired
    private KpiRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @Override
    public Kpi findKpiStaff(int staff_id, String kpi_name) {
        return repository.findKpiStaff(staff_id, kpi_name);
    }

    @Override
    public Kpi findKpiDepartment(int department_id, String kpi_name) {
        return repository.findKpiDepartment(department_id, kpi_name);
    }
    
    @Override
    public Kpi saveKpi(Kpi kpi) {
        return repository.save(kpi);
    }

    @Override
    public Optional<Kpi> findOne(int id) {
        return repository.findById(id);
    }

    @Override
    public ArrayList getKpiStaff(int department, int is_manager) {
        return repository.GetKpiStaff(department, is_manager);
    }

    @Override
    public ArrayList getKpiDepartment(int department, int is_manager) {
        return repository.GetKpiDepartment(department, is_manager);
    }
    
    @Override
    public void approveKpi(String is_approved, int id, int approved_by) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Kpi SET is_approved = ?1, approved_by = ?2 WHERE id = ?3")
                .setParameter(1, is_approved)
                .setParameter(2, approved_by)
                .setParameter(3, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public ArrayList getDetailOfKpi(int kpi_id) {
        return repository.GetDetailOfKpi(kpi_id);
    }

    @Override
    public void updateAt(Date update_at, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Kpi SET update_at = ?1 WHERE id = ?2")
                .setParameter(1, update_at)
                .setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void approvedBy(int approved_by, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Kpi SET approved_by = ?1 WHERE id = ?2")
                .setParameter(1, approved_by)
                .setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
