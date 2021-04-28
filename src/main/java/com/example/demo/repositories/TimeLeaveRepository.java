/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.TimeLeave;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Red
 */
public interface TimeLeaveRepository extends CrudRepository<TimeLeave, Integer>{
    @Query(value = "SELECT * FROM time_leave WHERE staff_id = ?1 and DATE_FORMAT(day_time_leave, '%Y-%m') = DATE_FORMAT(?2, '%Y-%m') ORDER BY day_time_leave", nativeQuery=true)
    List<TimeLeave> getListTimeLeave(int staff_id, Date day_time_leave);
    
    @Query(value = "SELECT * FROM time_leave WHERE staff_id = ?1 and day_time_leave = ?2 GROUP BY day_time_leave", nativeQuery=true)
    TimeLeave checkAddTime(int staff_id, Date day_time_leave);
    
    @Query(value = "SELECT * FROM time_leave WHERE id = ?1", nativeQuery=true)
    TimeLeave findTimeLeave(int id);
    
    @Query(value = "UPDATE time_leave SET day_time_leave = ?1, time = ?2, note = ?3 WHERE id = ?4", nativeQuery=true)
    TimeLeave updateTimeLeave(Date day_time_leave, Date time, String note, int id);

    @Query(value = "CALL GetStaffApprove(?1, ?2, ?3, ?4);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetStaffApprove(Date month_get, Integer department, Integer is_manager, Integer staff_id);
    
    @Query(value = "SELECT * FROM time_leave WHERE id = ?1", nativeQuery=true)
    TimeLeave findOne(int id);
    
    @Query(value = "CALL GetAllStaffTime(?1);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetAllStaffTime(Date month_get);
    
    @Query(value = "CALL GetAllStaffTimeFromTo(?1, ?2);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetAllStaffTimeFromTo(Date from_date, Date to_date);
    
    @Query(value = "CALL GetAllTimeLeaveFromTo(?1, ?2)", nativeQuery=true)
    ArrayList<Map<String, Object>> getTimeLeaveFromTo(Date from_date, Date to_date);
    
    @Transactional
    @Modifying
    @Query(value = "CALL DoneLeave(?1, ?2)", nativeQuery=true)
    void doneLeave(Date from_date, Date to_date);
    
    @Query(value = "CALL SummaryStaffTime(?1);", nativeQuery = true)
    ArrayList<Map<String, Object>> SummaryStaffTime(Date month_get);
    
    @Query(value = "CALL SummaryTimeLeave(?1);", nativeQuery = true)
    ArrayList<Map<String, Object>> SummaryTimeLeave(Date month_get);
    
    @Query(value = "SELECT * FROM time_leave WHERE staff_id = ?1 and day_time_leave >= ?2 and day_time_leave <= ?3", nativeQuery=true)
    List<TimeLeave> checkListTimeLeave(int staff_id, String from_date, String to_date);
    
    @Query(value = "CALL GetDetailTimeLeave(?1, ?2);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetDetailTimeLeaveAll(Date month_get, int staff_id);
    
    @Query(value = "CALL GetAllDetailTimeLeave(?1);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetAllDetailTimeLeave(Date month_get);
}
