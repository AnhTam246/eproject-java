/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Transfer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Red
 */
public interface TransferRepository extends CrudRepository<Transfer, Integer>{
    @Query(value = "CALL GetListTransfer(?1, ?2)", nativeQuery=true)
    ArrayList<Map<String, Object>> getListTransfer(Date month_get, int department);
    
    @Query(value = "SELECT * FROM transfer WHERE staff_id = ?1 AND (old_manager_approved = 0 or new_manager_approved = 0)", nativeQuery=true)
    Transfer getTransferCheck(int staff_id);
    
    @Query(value = "SELECT t.*, d.name_vn as old_department_name, d2.name_vn as new_department_name "
            + "FROM `transfer` as t "
            + "left join department as d on t.old_department = d.id "
            + "left join department as d2 on t.new_department = d2.id", nativeQuery=true)
    ArrayList<Map<String, Object>> getListTransferName(Date month_get, int department);
}
