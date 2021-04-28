/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.KpiDetailChild;
import java.util.List;

/**
 *
 * @author nfsre
 */
public interface KpiDetailChildService {
    List<KpiDetailChild> getKpiDetailChild(int kpi_detail_id);
    void clearAllDetailChild(int kpi_detail_id);
    void saveKpiDetailChild(KpiDetailChild kpiDetailChild);
}
