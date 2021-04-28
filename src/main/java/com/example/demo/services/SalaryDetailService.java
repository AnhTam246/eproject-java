/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.SalaryDetail;
import com.example.demo.repositories.ISalaryDetailRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tonamson
 */
@Service
public class SalaryDetailService implements ISalaryDetailService {

    @Autowired
    private ISalaryDetailRepository salaryDetailRepository;

    @Override
    public List<SalaryDetail> findBySalaryId(int salary_id) {
        return salaryDetailRepository.findBySalaryId(salary_id);
    }

    @Override
    public SalaryDetail save(SalaryDetail detail) {
        return salaryDetailRepository.save(detail);
    }

    @Override
    public void delete(int id) {
        salaryDetailRepository.delete(id);
    }

    @Override
    public boolean deleteBySalaryId(int id) {
        try {
            salaryDetailRepository.deleteBySalaryId(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public SalaryDetail findById(int id) {
        return salaryDetailRepository.findById(id);
    }

    @Override
    public List<SalaryDetail> findByStaffId(int staff_id) {
        return salaryDetailRepository.findByStaffId(staff_id);
    }

}
