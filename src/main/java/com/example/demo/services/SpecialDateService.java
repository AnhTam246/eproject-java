/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.SpecialDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Red
 */
public interface SpecialDateService {
    List<SpecialDate> getAllSpecialDate(Date special_date_from);
    void addSpecialDate(SpecialDate specialDate);
    void deleteSpecialDate(int id);
    SpecialDate findSpecialDate(int id);
    void updateSpecialDate(Date special_date_from, Date special_date_to, String note, int id);
    List<SpecialDate> checkSpecialDate(String day_check);
    ArrayList<Map<String, Object>> getRequestOT(Date special_date, int staff_request, int department_request);
    void approveOt(int is_approved, int id);
    Map<String, Object> getDetailOT(int special_date_id);
    void updateStaffOt(String string_staff_ot, int id);
    ArrayList<Map<String, Object>> GetListSpecialDate(Date y_m);
}
