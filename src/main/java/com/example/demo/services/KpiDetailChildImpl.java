/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.KpiDetailChild;
import com.example.demo.repositories.KpiDetailChildRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author nfsre
 */
@Service
public class KpiDetailChildImpl implements KpiDetailChildService{
    @Autowired
    private KpiDetailChildRepository repository;

    @Autowired
    EntityManagerFactory emf;
    
    @Override
    public List<KpiDetailChild> getKpiDetailChild(int kpi_detail_id) {
        return repository.findKpiDetailChild(kpi_detail_id);
    }
    
    @Override
    public void saveKpiDetailChild(KpiDetailChild kpiDetailChild) {
        repository.save(kpiDetailChild);
    }

    @Override
    public void clearAllDetailChild(int kpi_detail_id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM KpiDetailChild WHERE kpi_detail_id = ?1")
                .setParameter(1, kpi_detail_id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
    
}
