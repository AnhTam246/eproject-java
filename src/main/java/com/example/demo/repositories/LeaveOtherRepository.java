/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.LeaveOther;
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
public interface LeaveOtherRepository extends CrudRepository<LeaveOther, Integer>{
    @Query(value = "SELECT * FROM leave_other WHERE staff_id = ?1 AND date_format(?2,'%Y-%m') BETWEEN DATE_FORMAT(from_date,'%Y-%m') AND DATE_FORMAT(to_date,'%Y-%m')", nativeQuery=true)
    List<LeaveOther> getListLeaveOther(int staff_id, Date month_get);
    
    @Query(value = "CALL GetLeaveOtherFromTo(?1, ?2)", nativeQuery=true)
    ArrayList<Map<String, Object>> GetLeaveOtherFromTo(Date from_date, Date to_date);
    
    @Query(value = "CALL GetStaffApproveLeaveOther(?1, ?2, ?3, ?4);", nativeQuery = true)
    ArrayList<Map<String, Object>> GetStaffApproveLeaveOther(Date month_get, Integer department, Integer is_manager, Integer staff_id);
    
    @Query(value = "SELECT * FROM leave_other WHERE staff_id = ?1 "
            + "AND (((?2 BETWEEN from_date AND to_date) OR (?3 BETWEEN from_date AND to_date)) OR (?2 < from_date AND ?3 > to_date)) ", nativeQuery=true)
    LeaveOther checkDuplicate(Integer staff_id, Date from_date, Date to_date);
    
     @Query(value = "SELECT * FROM leave_other WHERE staff_id = ?1 "
            + "AND (((?2 BETWEEN from_date AND to_date) OR (?3 BETWEEN from_date AND to_date)) OR (?2 < from_date AND ?3 > to_date)) "
             + "AND id <> ?4", nativeQuery=true)
    LeaveOther checkDuplicateUpdate(Integer staff_id, Date from_date, Date to_date, Integer id);
}
