/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.SalaryOption;
import com.example.demo.repositories.ISalaryOptionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tonamson
 */
@Service
public class SalaryOptionService implements ISalaryOptionService {

    @Autowired
    private ISalaryOptionRepository salaryOptionRepository;

    @Override
    public List<SalaryOption> findByType(String type) {
        return salaryOptionRepository.findByType(type);
    }

    @Override
    public List<SalaryOption> findAll() {
        return salaryOptionRepository.findAll();
    }

    @Override
    public SalaryOption findByKey(String key) {
        return salaryOptionRepository.findByKey(key);
    }

}
