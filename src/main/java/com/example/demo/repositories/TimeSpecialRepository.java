/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.TimeSpecial;
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
public interface TimeSpecialRepository extends CrudRepository<TimeSpecial, Integer>{
    @Query(value = "SELECT * FROM time_special WHERE special_date_id = ?1", nativeQuery=true)
    List<TimeSpecial> getListTimeSpecial(int id_special_date);
    
    @Query(value = "CALL GetTimeSpecial(?1);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetTimeSpecial(Integer id_special_date);
    
    @Query(value = "CALL GetTimeSepcialFromTo(?1, ?2)", nativeQuery=true)
    ArrayList<Map<String, Object>> GetTimeSepcialFromTo(Date from_date, Date to_date);
}
