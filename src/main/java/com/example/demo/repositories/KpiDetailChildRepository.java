/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.KpiDetailChild;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author nfsre
 */
public interface KpiDetailChildRepository extends CrudRepository<KpiDetailChild, Integer>{
    @Query(value = "SELECT * FROM kpi_detail_child WHERE kpi_detail_id = ?1", nativeQuery=true)
    List<KpiDetailChild> findKpiDetailChild(int kpi_detail_id);
    @Query(value = "DELETE FROM kpi_detail_child WHERE kpi_detail_id = ?1", nativeQuery=true)
    void clearAllDetailChild(int kpi_detail_id);
}

