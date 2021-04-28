/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.SalaryDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tonamson
 */
public interface ISalaryDetailRepository extends JpaRepository<SalaryDetail, Integer> {
    
    SalaryDetail save(SalaryDetail detail);
    
    @Transactional
    @Modifying
    @Query(value = "delete SalaryDetail s where s.id = :id")
    void delete(@Param("id") Integer id);
    
    
    @Transactional
    @Modifying
    @Query(value = "delete SalaryDetail s where s.salaryId = :salary_id")
    void deleteBySalaryId(@Param("salary_id") Integer salary_id);
    
    @Transactional
    @Modifying
    @Query(value = "from SalaryDetail s where s.salaryId = :salary_id")
    List<SalaryDetail> findBySalaryId(@Param("salary_id") Integer salary_id);
    
    SalaryDetail findById(int id);
    
    @Transactional
    @Modifying
    @Query(value = "from SalaryDetail s where s.staffId = :staff_id")
    List<SalaryDetail> findByStaffId(@Param("staff_id") Integer staff_id);
}
