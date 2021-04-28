/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.CheckInOut;
import com.example.demo.repositories.CheckInOutRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Red
 */
@Service
public class CheckInOutImpl implements CheckInOutService{
    @Autowired
    private CheckInOutRepository repository;
    
    @Autowired
    EntityManagerFactory emf;
    
    @Override
    public void save(CheckInOut check) {
        repository.save(check);
    }

    @Override
    public boolean checkCheckIn(int staff_id, String check_in_day) {
        try {
            Date date_check_in_day = new SimpleDateFormat("yyyy-MM-dd").parse(check_in_day);
            CheckInOut check_check_in_out = repository.checkCheckIn(staff_id, date_check_in_day);
            if(check_check_in_out == null) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList getStaffTime(int staff_id, String y_m) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(y_m);
            return repository.GetStaffTime(staff_id, time);
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean checkCheckInLeaveOther(Integer staff_id, String from_date, String to_date) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            CheckInOut check_check_in_out = repository.checkCheckInLeaveOther(staff_id, from, to);
            if(check_check_in_out == null) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
}
