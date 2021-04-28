/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.TimeSpecial;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Red
 */
public interface TimeSpecialService {
    void saveTimeSpecial(TimeSpecial timeSpecial);
    ArrayList<Map<String, Object>> GetTimeSpecial(Integer id_special_date);
    ArrayList<Map<String, Object>> GetTimeSepcialFromTo(Date from_date, Date to_date);
}
