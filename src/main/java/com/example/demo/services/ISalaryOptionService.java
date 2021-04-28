/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.SalaryOption;
import java.util.List;

/**
 *
 * @author tonamson
 */
public interface ISalaryOptionService {

    List<SalaryOption> findByType(String type);

    List<SalaryOption> findAll();

    SalaryOption findByKey(String key);

}
