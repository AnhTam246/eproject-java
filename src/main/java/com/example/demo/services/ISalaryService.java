/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Salary;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tonamson
 */
public interface ISalaryService {

    List<Salary> findAll();

    Salary findById(int id);

    Salary save(Salary salary);

    boolean deleteSalaryById(int id);

    boolean checkDateInRange(Date date);

    Salary checkSimilarDate(Date fromDate, Date toDate);
    
    Salary checkFromDateAndToDateSuccess(Date fromDate, Date toDate);

    boolean updateStatus(int id, String status);

    ArrayList<Map<String, Object>> getAdditionalTimekeepingFromTo(String from_date, String to_date);
}
