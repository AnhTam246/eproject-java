/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.SalaryDetail;
import java.util.List;

/**
 *
 * @author tonamson
 */
public interface ISalaryDetailService {

    SalaryDetail save(SalaryDetail detail);

    List<SalaryDetail> findBySalaryId(int salary_id);

    List<SalaryDetail> findByStaffId(int staff_id);

    void delete(int id);

    boolean deleteBySalaryId(int id);

    SalaryDetail findById(int id);
}
