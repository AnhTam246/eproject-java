/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.TimeLeave;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Red
 */
public interface TimeLeaveService {
    TimeLeave findOne(int id);
    List<TimeLeave> getListTimeLeave(int staff_id, String day_time_leave);
    void addTimeLeave(TimeLeave timeleave);
    TimeLeave checkAddTime(int staff_id, Date day_time_leave);
    boolean deleteTime(int id);
    TimeLeave findTimeLeave(int id);
    void updateTimeLeave(Date day_time_leave, Date time, String note, int id, String image);
    void updateDayTimeLeaveOfStaff(int id);
    void revertDayTimeLeaveOfStaff(int id);
    ArrayList getStaffApprove(String day_time_leave, Integer department, Integer is_manager, Integer staff_id);
    List<HashMap<String, Object>> getDetailStaffApprove(int id);
    void approveTimeLeave(String is_approved, int id);
    void adminApproveTimeLeave(String is_approved, int id, Date day_approve);
    ArrayList getAllStaffTime(String y_m);
    ArrayList getAllStaffTimeFromTo(String from_date, String to_date);
    ArrayList<Map<String, Object>> getTimeLeaveFromTo(String from_date, String to_date);
    void doneLeave(String from_date, String to_date);
    ArrayList<Map<String, Object>> SummaryStaffTime(String y_m);
    ArrayList<Map<String, Object>> SummaryTimeLeave(String y_m);
    List<TimeLeave> checkListTimeLeave(int staff_id, String from_date, String to_date);
    ArrayList<Map<String, Object>> GetDetailTimeLeaveAll(String month_get, int staff_id);
    ArrayList<Map<String, Object>> GetAllDetailTimeLeave(String month_get);
}
