/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.LeaveOther;
import com.example.demo.entities.TimeLeave;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.CheckInOutService;
import com.example.demo.services.LeaveOtherService;
import com.example.demo.services.TimeLeaveService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Red
 */
@RestController
@RequestMapping("/leave-other")
public class LeaveOtherController {
    @Autowired
    private LeaveOtherService service;
    
    @Autowired
    private TimeLeaveService timeLeaveService;
    
    @Autowired
    private CheckInOutService checkInOutService;

    private LeaveOtherController(LeaveOtherService service, TimeLeaveService timeLeaveService, CheckInOutService checkInOutService) {
        this.service = service;
        this.timeLeaveService = timeLeaveService;
        this.checkInOutService = checkInOutService;
    }
      
    @PostMapping(path = "/check-list-time-leave")
    public ResponseEntity<Object> check(@RequestBody HashMap<String,Object> body) {
        try {            
            List<TimeLeave> list = timeLeaveService.checkListTimeLeave(Integer.parseInt(body.get("staff_id").toString()), body.get("day_leave_from").toString(), body.get("day_leave_to").toString());
           
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get success", list);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get fail", e);
        }
    }
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addLeave(@RequestBody HashMap<String,Object> body) {
        try {
            Date day_leave_from = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_leave_from").toString());
            Date day_leave_to = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_leave_to").toString());
            Date created_at = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("created_at").toString());
            
            if(checkInOutService.checkCheckInLeaveOther(Integer.parseInt(body.get("staff_id").toString()), body.get("day_leave_from").toString(), body.get("day_leave_to").toString()) == false) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Added time");
            }
            
            //Update
            if(body.get("id_update") != null) {
                if(service.checkDuplicateUpdate(Integer.parseInt(body.get("staff_id").toString()), body.get("day_leave_from").toString(), body.get("day_leave_to").toString(), Integer.parseInt(body.get("id_update").toString())) == false) {
                    return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Duplicate leave");
                }
            } else {                    
                if(service.checkDuplicate(Integer.parseInt(body.get("staff_id").toString()), body.get("day_leave_from").toString(), body.get("day_leave_to").toString()) == false) {
                    return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Duplicate leave");
                }
            }
         
            LeaveOther leaveOther = new LeaveOther();
            if(body.get("id_update") != null){
                leaveOther.setId(Integer.parseInt(body.get("id_update").toString()));
            }
            leaveOther.setStaffId(Integer.parseInt(body.get("staff_id").toString()));
            leaveOther.setTypeLeave(Integer.parseInt(body.get("type_leave").toString()));
            leaveOther.setFromDate(day_leave_from);
            leaveOther.setToDate(day_leave_to);
            leaveOther.setImage(body.get("image").toString());
            leaveOther.setNote(body.get("note").toString());
            leaveOther.setIsApproved(Integer.parseInt(body.get("is_approved").toString()));
            leaveOther.setCreatedAt(created_at);
            leaveOther.setDone(0);
            
            service.add(leaveOther);
            
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Add Other Leave Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", e);
        }
    }
    
    @GetMapping(path = "/list")
    public ResponseEntity<Object> listTimeLeave(@RequestParam int staff_id, @RequestParam String month_get) {
        try {
            Date month = new SimpleDateFormat("yyyy-MM-dd").parse(month_get);
            List<LeaveOther> list = service.list(staff_id, month);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", list);
        } catch (ParseException ex) {
            Logger.getLogger(LeaveOtherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data error", "Error");
    }
    
    @GetMapping(path = "/get-leave-other-from-to")
    public ResponseEntity<Object> GetLeaveOtherFromTo(@RequestParam String from_date, @RequestParam String to_date) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.GetLeaveOtherFromTo(from_date, to_date));
    }
    
    @PostMapping(path = "/get-staff-approve")
    public ResponseEntity<Object> getStaffApprove(@RequestBody HashMap<String,Object> body) {
        ArrayList staff = service.GetStaffApproveLeaveOther(body.get("day_time_leave").toString(), Integer.parseInt(body.get("department").toString()), Integer.parseInt(body.get("is_manager").toString()), Integer.parseInt(body.get("staff_id").toString()));
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", staff);
    }
    
    @GetMapping(path = "/detail-time-staff-approve")
    public ResponseEntity<Object> findDetailStaffTimeLeave(@RequestParam int id) {
        List<HashMap<String, Object>> leave_other = service.getDetailOtherLeave(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get detail time staff approve success", leave_other);
    }  
    
    @PostMapping(path = "/approve-leave-other")
    public ResponseEntity<Object> approveTimeLeave(@RequestBody HashMap<String,Object> body) {
        try {      
            if(body.get("day_approved") == null) {
                service.approveLeaveOther(body.get("is_approved").toString(), Integer.parseInt(body.get("id").toString()));
            } else {
                Date day_approved = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_approved").toString());
                service.adminApproveLeaveOther(body.get("is_approved").toString(), Integer.parseInt(body.get("id").toString()), day_approved);
            }
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve fail", e);
        }
    }
    
    @GetMapping(path = "/get-detail")
    public ResponseEntity<Object> getLeaveOther(@RequestParam int id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.findLeaveOther(id));
    }
    
    @PostMapping(path = "/delete-leave-other")
    public ResponseEntity<Object> deleteTimeLeave(@RequestBody HashMap<String,Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        service.deleteLeaveOther(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", "Delete success");
    }  
}
