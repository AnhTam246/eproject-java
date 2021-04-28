/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Kpi;
import com.example.demo.entities.KpiDetail;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Red
 */
public interface KpiService {
    Kpi findKpiStaff(int staff_id, String kpi_name);
    Kpi findKpiDepartment(int department_id, String kpi_name);
    Kpi saveKpi(Kpi kpi);
    Optional<Kpi> findOne(int id);
    ArrayList getKpiStaff(int department, int is_manager);
    ArrayList getKpiDepartment(int department, int is_manager);
    void approveKpi(String is_approved, int id, int approved_by);
    ArrayList getDetailOfKpi(int kpi_id);
    void updateAt(Date update_at, int id);
    void approvedBy(int approved_by, int id);
}
