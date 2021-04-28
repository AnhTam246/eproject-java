/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.TimeSpecial;
import com.example.demo.repositories.TimeSpecialRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Red
 */
@Service
public class TimeSpecialImpl implements TimeSpecialService{
    @Autowired
    private TimeSpecialRepository repository;
    
    @Autowired
    EntityManagerFactory emf;

    @Override
    public void saveTimeSpecial(TimeSpecial timeSpecial) {
        repository.save(timeSpecial);
    }

    @Override
    public ArrayList<Map<String, Object>> GetTimeSpecial(Integer id_special_date) {
        return repository.GetTimeSpecial(id_special_date);
    }

    @Override
    public ArrayList<Map<String, Object>> GetTimeSepcialFromTo(Date from_date, Date to_date) {
        return repository.GetTimeSepcialFromTo(from_date, to_date);
    }
      
}
