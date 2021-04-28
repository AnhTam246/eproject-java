/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.KpiDetail;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Red
 */
public interface KpiDetailService {
    List<KpiDetail> getKpiDetailByKpiId(int kpi_id);
    void saveKpiDetail(KpiDetail kpiDetail);
    Optional<KpiDetail> getOne(int id);
}
