/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.TimeSpecial;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.TimeSpecialService;
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
@RequestMapping("/time-special")
public class TimeSpecialController {
    
    @Autowired
    private TimeSpecialService service;

    private TimeSpecialController(TimeSpecialService service) {
        this.service = service;
    }
    
    @PostMapping(path = "/save-time-special")
    public ResponseEntity<Object> saveTimeSpecial(@RequestBody HashMap<String,Object> body) {
        try {
            
            //Save Kpi Details
            ArrayList<HashMap> time_specials = (ArrayList) body.get("list_time_special");
            
            for(int i=0; i < time_specials.size(); i++){
                TimeSpecial time_special = new TimeSpecial();
                //Create kpi detail with new kpi
                time_special.setStaffId(Integer.parseInt(time_specials.get(i).get("staff_id").toString()));
                time_special.setSpecialDateId(Integer.parseInt(time_specials.get(i).get("special_date_id").toString()));
                Date day_time_special = new SimpleDateFormat("yyyy-MM-dd").parse(time_specials.get(i).get("day_time_special").toString());
                time_special.setDayTimeSpecial(day_time_special);
                time_special.setNumberTime(Integer.parseInt(time_specials.get(i).get("number_time").toString()));
                time_special.setMultiply(Integer.parseInt(time_specials.get(i).get("multiply").toString()));
                Date day_create = new SimpleDateFormat("yyyy-MM-dd").parse(time_specials.get(i).get("day_create").toString());
                time_special.setDateCreate(day_create);         
                
                try {
                    service.saveTimeSpecial(time_special);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Success");
        } catch (Exception ex) {
             Logger.getLogger(TimeSpecialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Error");
    }
    
    @GetMapping(path = "/get-list-time-special")
    public ResponseEntity<Object> SummaryTimeLeave(@RequestParam int special_date_id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.GetTimeSpecial(special_date_id));
    }
    
    @GetMapping(path = "/get-time-special-from-to")
    public ResponseEntity<Object> getTimeSpecialFromTo(@RequestParam String from_date, @RequestParam String to_date) {
        try {
            Date from = new SimpleDateFormat("yyyy-MM-dd").parse(from_date);
            Date to = new SimpleDateFormat("yyyy-MM-dd").parse(to_date);
            ArrayList<Map<String, Object>> list = service.GetTimeSepcialFromTo(from, to);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
        } catch (ParseException ex) {
            Logger.getLogger(TimeSpecialController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Error", "Fail");
    }
}
