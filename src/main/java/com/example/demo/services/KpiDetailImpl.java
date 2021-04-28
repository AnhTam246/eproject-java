/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.KpiDetail;
import com.example.demo.repositories.KpiDetailRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Red
 */
@Service
public class KpiDetailImpl implements KpiDetailService{
    @Autowired
    private KpiDetailRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<KpiDetail> getKpiDetailByKpiId(int kpi_id) {
        return repository.findKpiDetail(kpi_id);
    }

    @Override
    public void saveKpiDetail(KpiDetail kpiDetail) {
        repository.save(kpiDetail);
    }

    @Override
    public Optional<KpiDetail> getOne(int id) {
        return repository.findById(id);
    }
    
}
