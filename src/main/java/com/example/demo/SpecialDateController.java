/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.SpecialDate;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.SpecialDateService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/special-date")
public class SpecialDateController {
    @Autowired
    private SpecialDateService service;

    private SpecialDateController(SpecialDateService service) {
        this.service = service;
    }
    
    @GetMapping(path = "/list")
    public ResponseEntity<Object> listTimeLeave(@RequestParam String special_date_from) {
        try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(special_date_from);
            List<SpecialDate> list = service.getAllSpecialDate(time);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", list);
        } catch (ParseException ex) {
            Logger.getLogger(SpecialDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get data error");
    }
    
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addSpecialDate(@RequestBody HashMap<String,Object> body) {
        try {
            Date day_special_from = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_special_from").toString());
            Date day_special_to = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_special_to").toString());
            int type_day = Integer.parseInt(body.get("type_day").toString());
            
            if(type_day == 1) {
                SpecialDate specialDate = new SpecialDate();
                specialDate.setDaySpecialFrom(day_special_from);
                specialDate.setDaySpecialTo(day_special_to);
                specialDate.setNote(body.get("note").toString());
                specialDate.setTypeDay(type_day);
                service.addSpecialDate(specialDate);
            } else {
                SpecialDate specialDate = new SpecialDate();
                specialDate.setDaySpecialFrom(day_special_from);
                specialDate.setDaySpecialTo(day_special_to);
                specialDate.setNote(body.get("note").toString());
                specialDate.setTypeDay(type_day);
                specialDate.setStaffRequest(Integer.parseInt(body.get("staff_request").toString()));
                specialDate.setDepartmentRequest(Integer.parseInt(body.get("department_request").toString()));
                specialDate.setIsApproved(Integer.parseInt(body.get("is_approved").toString()));
                specialDate.setStaffOt(body.get("string_staff_ot").toString());
                service.addSpecialDate(specialDate);
            }
         
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save SpecialDate success", "Add SpecialDate Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save SpecialDate fail", e.toString());
        }
    }
    
    @PostMapping(path = "/delete")
    public ResponseEntity<Object> deleteSpecialDate(@RequestBody HashMap<String,Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        service.deleteSpecialDate(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Delete Special Date success", "Delete success");
    }  
    
    @GetMapping(path = "/detail")
    public ResponseEntity<Object> findSpecialDate(@RequestParam int id) {
        SpecialDate specialDate = service.findSpecialDate(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", specialDate);
    }  
    
    @PostMapping(path = "/update")
    public ResponseEntity<Object> updateSpecialDate(@RequestBody HashMap<String,Object> body) {
        try {
            Date day_special_from = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_special_from").toString());
            Date day_special_to = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("day_special_to").toString());
            
            service.updateSpecialDate(day_special_from, day_special_to, body.get("note").toString(), Integer.parseInt(body.get("id").toString()));
            if(body.get("string_staff_ot") != null) {
                service.updateStaffOt(body.get("string_staff_ot").toString(), Integer.parseInt(body.get("id").toString()));
            }
            
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update Special Date success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update Special Date fail", e);
        }
    }
    
    @GetMapping(path = "/check-day")
    public ResponseEntity<Object> findSpecialDate(@RequestParam String day_check) {
        List<SpecialDate> list = service.checkSpecialDate(day_check);
        System.out.println(list);
        if(list.isEmpty()) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Day not special date", "No");
        } else {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Day is special date", "Yes");
        }
    } 
    
    @GetMapping(path = "/get-request-ot")
    public ResponseEntity<Object> listContractByStaff(@RequestParam String special_date_from, @RequestParam int staff_request, @RequestParam int department_request) {
      try {
            Date time = new SimpleDateFormat("yyyy-MM-dd").parse(special_date_from);
            
            ArrayList<Map<String, Object>> list = service.getRequestOT(time, staff_request, department_request);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", list);
        } catch (ParseException ex) {
            Logger.getLogger(SpecialDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get data error");
    }
    
    @GetMapping(path = "/detail-ot")
    public ResponseEntity<Object> getDetailOt(@RequestParam int id) {
        Map<String, Object> specialDate = service.getDetailOT(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", specialDate);
    }  
    
    @PostMapping(path = "/approve-ot")
    public ResponseEntity<Object> approveTimeLeave(@RequestBody HashMap<String,Object> body) {
        try {      
            service.approveOt(Integer.parseInt(body.get("is_approved").toString()), Integer.parseInt(body.get("id").toString()));
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve fail", e);
        }
    }
    
    @GetMapping(path = "/list-special-date")
    public ResponseEntity<Object> listSpecialDate(@RequestParam String special_date_from) {
        try {
            Date special_date = new SimpleDateFormat("yyyy-MM-dd").parse(special_date_from);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", service.GetListSpecialDate(special_date));
        } catch (ParseException ex) {
            Logger.getLogger(SpecialDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get data error");
    }
}
