/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.SpecialDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Red
 */
public interface SpecialDateRepository extends CrudRepository<SpecialDate, Integer> {

    @Query(value = "SELECT * FROM special_date WHERE DATE_FORMAT(day_special_from, '%Y') = DATE_FORMAT(?1, '%Y')", nativeQuery = true)
    List<SpecialDate> findAll(Date special_date);

    @Query(value = "SELECT * FROM special_date WHERE id = ?1", nativeQuery = true)
    SpecialDate findSpecialDate(int id);

    @Query(value = "SELECT * FROM special_date WHERE ?1 BETWEEN day_special_from AND day_special_to AND type_day = 1", nativeQuery = true)
    List<SpecialDate> checkSpecialDate(Date day_check);
    
    @Query(value = "CALL GetRequestOT(?1, ?2, ?3);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetRequestOT(Date special_date, int staff_request, int department_request);
    
    @Query(value = "CALL GetDetailOT(?1);", nativeQuery = true)
    Map<String, Object> GetDetailOT(int special_date_id);
    
    @Query(value = "CALL GetListSpecialDate(?1);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetListSpecialDate(Date month_get);
}
