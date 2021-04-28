/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.LeaveOther;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Red
 */
public interface LeaveOtherService {
    void add(LeaveOther leaveOther);
    List<LeaveOther> list(int staff_id, Date month_get);
    ArrayList<Map<String, Object>> GetLeaveOtherFromTo(String from_date, String to_date);
    ArrayList<Map<String, Object>> GetStaffApproveLeaveOther(String day_time_leave, Integer department, Integer is_manager, Integer staff_id);
    List<HashMap<String, Object>> getDetailOtherLeave(int id);
    void approveLeaveOther(String is_approved, int id);
    public void adminApproveLeaveOther(String is_approved, int id, Date day_approve);
    Optional<LeaveOther> findLeaveOther(int id);
    boolean deleteLeaveOther(int id);
    boolean checkDuplicate(int staff_id, String from_date, String to_date);
    boolean checkDuplicateUpdate(int staff_id, String from_date, String to_date, int id);
}
