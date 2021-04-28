/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Salary;
import com.example.demo.repositories.ISalaryRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tonamson
 */
@Service
public class SalaryService implements ISalaryService {

    @Autowired
    private ISalaryRepository salaryRepository;

    @Override
    public List<Salary> findAll() {
        return salaryRepository.findAll();
    }

    @Override
    public boolean deleteSalaryById(int id) {
        try {
            salaryRepository.deleteSalaryById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Salary save(Salary salary) {
        return salaryRepository.save(salary);
    }

    @Override
    public boolean checkDateInRange(Date date) {
        List<Salary> lst = salaryRepository.findDateInRange(date);
        return lst.size() > 0;
    }

    @Override
    public Salary checkSimilarDate(Date fromDate, Date toDate) {
        List<Salary> salaries = salaryRepository.findByFromDateAndToDate(fromDate, toDate);
        return salaries.size() == 1 ? salaries.get(0) : null;
    }

    @Override
    public Salary findById(int id) {
        return salaryRepository.findById(id);
    }

    @Override
    public boolean updateStatus(int id, String status) {
        try {
            salaryRepository.updateStatus(id, status);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Map<String, Object>> getAdditionalTimekeepingFromTo(String from_date, String to_date) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            return salaryRepository.getAdditionalTimekeepingFromTo(from, to);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Salary checkFromDateAndToDateSuccess(Date fromDate, Date toDate) {
        List<Salary> salaries = salaryRepository.findHaveFromDateAndToDateSuccess(fromDate, toDate);
        return salaries.size() == 1 ? salaries.get(0) : null;
    }
}
