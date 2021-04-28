/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Salary;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonamson
 */
public interface ISalaryRepository extends JpaRepository<Salary, Integer> {

    Salary save(Salary salary);

    Salary findById(int id);

    @Transactional
    @Modifying
    @Query(value = "delete Salary s where s.id = :id")
    void deleteSalaryById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "from Salary s where s.fromDate <= :date and :date <= s.toDate and status = 'success'")
    List<Salary> findDateInRange(@Param("date") Date date);
    
    @Transactional
    @Modifying
    @Query(value = "from Salary s where s.fromDate = :from_date and s.toDate = :to_date")
    List<Salary> findByFromDateAndToDate(@Param("from_date") Date fromDate,@Param("to_date") Date toDate);
    
    @Transactional
    @Modifying
    @Query(value = "from Salary s where s.fromDate = :from_date and s.toDate = :to_date and status = 'success'")
    List<Salary> findHaveFromDateAndToDateSuccess(@Param("from_date") Date fromDate,@Param("to_date") Date toDate);

    @Transactional
    @Modifying
    @Query(value = "update Salary s set s.status = :status where s.id = :id")
    void updateStatus(@Param("id") Integer id, @Param("status") String status);
    
    
    @Query(value = "CALL GetAdditionalTimekeepingFromTo(?1, ?2)", nativeQuery=true)
    ArrayList<Map<String, Object>> getAdditionalTimekeepingFromTo(Date from_date, Date to_date);

}
