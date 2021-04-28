/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Kpi;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Red
 */
public interface KpiRepository extends CrudRepository<Kpi, Integer>{
    @Query(value = "SELECT * FROM kpi WHERE staff_id = ?1 AND kpi_name LIKE ?2", nativeQuery=true)
    Kpi findKpiStaff(int staff_id, String kpi_name);
    @Query(value = "SELECT * FROM kpi WHERE department_id = ?1 AND kpi_name LIKE ?2", nativeQuery=true)
    Kpi findKpiDepartment(int department_id, String kpi_name);
    @Query(value = "CALL GetKpiStaff(?1, ?2);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetKpiStaff(Integer department, Integer is_manager);
    @Query(value = "CALL GetKpiDepartment(?1, ?2);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetKpiDepartment(Integer department, Integer is_manager);
    @Query(value = "CALL GetDetailOfKpi(?1);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetDetailOfKpi(Integer kpi_id);
}
