/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.SalaryOption;
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
public interface ISalaryOptionRepository extends JpaRepository<SalaryOption, Integer> {

    @Override
    List<SalaryOption> findAll();

    @Transactional
    @Modifying
    @Query(value = "from SalaryOption s where s.type = :type")
    List<SalaryOption> findByType(@Param("type") String type);

    SalaryOption findByKey(@Param("key") String key);
}
