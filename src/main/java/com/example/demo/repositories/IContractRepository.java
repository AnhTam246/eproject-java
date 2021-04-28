/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Contract;
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
public interface IContractRepository extends JpaRepository<Contract, Integer> {

    Contract findById(int id);
    
    @Query(value = "SELECT * FROM contract WHERE staff_id = :staff_id ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Contract findOneByStaffId(int staff_id);

    Contract save(Contract contract);

    List<Contract> findAll();

    List<Contract> findByStaffIdIs(int staff_id);

    @Query(value = "SELECT * FROM contract WHERE staff_id = :staff_id AND start_date <= :date AND :date <= end_date ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Contract findOneByStaffId(@Param("staff_id") Integer staff_id, @Param("date") String date);

    @Transactional
    @Modifying
    @Query(value = "delete Contract c where c.id = :id")
    void deleteContract(@Param("id") Integer id);

}
