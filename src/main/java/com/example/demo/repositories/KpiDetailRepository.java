/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.KpiDetail;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Red
 */
public interface KpiDetailRepository extends CrudRepository<KpiDetail, Integer>{
    @Query(value = "SELECT * FROM kpi_detail WHERE kpi_id = ?1 AND del = 0", nativeQuery=true)
    List<KpiDetail> findKpiDetail(int kpi_id);
}
