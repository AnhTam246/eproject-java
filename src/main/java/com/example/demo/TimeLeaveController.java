/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.TimeLeave;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.CheckInOutService;
import com.example.demo.services.TimeLeaveService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/time-leave")
public class TimeLeaveController {
    @Autowired
    private TimeLeaveService service;
    @Autowired
    private CheckInOutService checkInOutService;

    private TimeLeaveController(TimeLeaveService service, CheckInOutService checkInOutService) {
        this.service = service;
        this.checkInOutService = checkInOutService;
    }
    
    @PostMapping(path = "/list")
    public ResponseEntity<Object> listTimeLeave(@RequestBody HashMap<String,Object> body) {
        int staff_id = Integer.parseInt(body.get("staff_id").toString());
        String day_time_leave = body.get("day_time_leave").toString();
        List<TimeLeave> list = service.getListTimeLeave(staff_id, day_time_leave);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", list);
    }
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addTimeLeave(@RequestBody HashMap<String,Object> body) {
        try {
            Date day_time_leave = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_time_leave").toString());
            Date time_leave = new SimpleDateFormat("HH:mm:ss").parse(body.get("time").toString());
            Date created_at = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("created_at").toString());
            
//            if(checkInOutService.checkCheckIn(Integer.parseInt(body.get("staff_id").toString()), body.get("day_time_leave").toString()) == false) {
//                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Added time");
//            }

            TimeLeave timeleave = new TimeLeave();
            timeleave.setStaffId(Integer.parseInt(body.get("staff_id").toString()));
            timeleave.setStaffCode(body.get("staff_code").toString());
            timeleave.setDayTimeLeave(day_time_leave);
            timeleave.setTime(time_leave);
            timeleave.setImage(body.get("image").toString());
            timeleave.setType(Boolean.parseBoolean(body.get("type").toString()));
            timeleave.setNote(body.get("note").toString());
            timeleave.setIsApproved(Integer.parseInt(body.get("is_approved").toString()));
            timeleave.setCreatedAt(created_at);
            timeleave.setDone(0);
            service.addTimeLeave(timeleave);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", e.toString());
        }
    }
             
    @PostMapping(path = "/delete")
    public ResponseEntity<Object> deleteTimeLeave(@RequestBody HashMap<String,Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        TimeLeave time_leave_delete = service.findOne(id);
        if(time_leave_delete.getType() == true) {
            service.revertDayTimeLeaveOfStaff(time_leave_delete.getStaffId());
        }
        service.deleteTime(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", "Delete success");
    }  
    
    @GetMapping(path = "/detail")
    public ResponseEntity<Object> findTimeLeave(@RequestParam int id) {
        TimeLeave timeleave = service.findTimeLeave(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", timeleave);
    }  
    
    @PostMapping(path = "/update")
    public ResponseEntity<Object> updateTimeLeave(@RequestBody HashMap<String,Object> body) {
        try {
            Date day_time_leave = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_time_leave").toString());
            Date time_leave = new SimpleDateFormat("HH:mm:ss").parse(body.get("time").toString());
            
//            if(checkInOutService.checkCheckIn(Integer.parseInt(body.get("staff_id").toString()), body.get("day_time_leave").toString()) == false) {
//                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Added time");
//            }
                        
            service.updateTimeLeave(day_time_leave, time_leave, body.get("note").toString(), Integer.parseInt(body.get("id").toString()), body.get("image").toString());
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update fail", e);
        }
    }
    
    @PostMapping(path = "/addLeave")
    public ResponseEntity<Object> addLeave(@RequestBody HashMap<String,Object> body) {
        try {
            Date day_time_leave = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_time_leave").toString());
            Date time_leave = new SimpleDateFormat("HH:mm:ss").parse(body.get("time").toString());
            
            if(checkInOutService.checkCheckIn(Integer.parseInt(body.get("staff_id").toString()), body.get("day_time_leave").toString()) == false) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Added time");
            }
            
            TimeLeave timeleave = new TimeLeave();
            timeleave.setStaffId(Integer.parseInt(body.get("staff_id").toString()));
            timeleave.setStaffCode(body.get("staff_code").toString());
            timeleave.setDayTimeLeave(day_time_leave);
            timeleave.setTime(time_leave);
            timeleave.setType(Boolean.parseBoolean(body.get("type").toString()));
            timeleave.setNote(body.get("note").toString());
            timeleave.setIsApproved(Integer.parseInt(body.get("is_approved").toString()));
            timeleave.setDone(0);
            service.addTimeLeave(timeleave);
            
            //-1 phep
            service.updateDayTimeLeaveOfStaff(Integer.parseInt(body.get("staff_id").toString()));
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", e);
        }
    }

    @PostMapping(path = "/get-staff-approve")
    public ResponseEntity<Object> getStaffApprove(@RequestBody HashMap<String,Object> body) {
        ArrayList staff = service.getStaffApprove(body.get("day_time_leave").toString(), Integer.parseInt(body.get("department").toString()), Integer.parseInt(body.get("is_manager").toString()), Integer.parseInt(body.get("staff_id").toString()));
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", staff);
    }
    
    @GetMapping(path = "/detail-time-staff-approve")
    public ResponseEntity<Object> findDetailStaffTimeLeave(@RequestParam int id) {
        List<HashMap<String, Object>> timeleave_staff = service.getDetailStaffApprove(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get detail time staff approve success", timeleave_staff);
    }  
    
    @PostMapping(path = "/approve-time-leave")
    public ResponseEntity<Object> approveTimeLeave(@RequestBody HashMap<String,Object> body) {
        try {      
            if(body.get("day_approved") == null) {
                service.approveTimeLeave(body.get("is_approved").toString(), Integer.parseInt(body.get("id").toString()));
            } else {
                Date day_approved = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_approved").toString());
                service.adminApproveTimeLeave(body.get("is_approved").toString(), Integer.parseInt(body.get("id").toString()), day_approved);
            }
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve fail", e);
        }
    }
    
    @GetMapping(path = "/get-all-staff-time")
    public ResponseEntity<Object> getStaffTime(@RequestParam String y_m) {
        ArrayList all_staff_time = service.getAllStaffTime(y_m);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", all_staff_time);
    }
    
    @GetMapping(path = "/get-all-staff-time-from-to")
    public ResponseEntity<Object> getStaffTimeFromTo(@RequestParam String from_date, @RequestParam String to_date) {
        ArrayList all_staff_time = service.getAllStaffTimeFromTo(from_date, to_date);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", all_staff_time);
    }
    
    @GetMapping(path = "/get-time-leave-from-to")
    public ResponseEntity<Object> getTimeLeaveFromTo(@RequestParam String from_date, @RequestParam String to_date) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.getTimeLeaveFromTo(from_date, to_date));
    }
    
    @GetMapping(path = "/done-leave")
    public ResponseEntity<Object> doneLeave(@RequestParam String from_date, @RequestParam String to_date) {
        service.doneLeave(from_date, to_date);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", "Done Leave");
    }
    
    @GetMapping(path = "/summary-staff-time")
    public ResponseEntity<Object> SummaryStaffTime(@RequestParam String y_m) {
        ArrayList summary = service.SummaryStaffTime(y_m);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", summary);
    }
    
    @GetMapping(path = "/summary-time-leave")
    public ResponseEntity<Object> SummaryTimeLeave(@RequestParam String y_m) {
        ArrayList summary = service.SummaryTimeLeave(y_m);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", summary);
    }
    
    @GetMapping(path = "/detail-time-leave-all")
    public ResponseEntity<Object> findTimeLeave(@RequestParam String month_get, @RequestParam int staff_id) {
        ArrayList timeLeave = service.GetDetailTimeLeaveAll(month_get, staff_id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", timeLeave);
    } 
    
    @GetMapping(path = "/all-detail-time-leave-export")
    public ResponseEntity<Object> exportTimeLeave(@RequestParam String month_get) {
        ArrayList timeLeave = service.GetAllDetailTimeLeave(month_get);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", timeLeave);
    } 
}