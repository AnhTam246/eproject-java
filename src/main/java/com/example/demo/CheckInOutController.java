/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.CheckInOut;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.CheckInOutService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
@RequestMapping("/check-in-out")
public class CheckInOutController {

    @Autowired
    private CheckInOutService service;

    private CheckInOutController(CheckInOutService service) {
        this.service = service;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> createCheckIn(@RequestBody HashMap<String, Object> body) {
        try {
            CheckInOut check = new CheckInOut();
            boolean type = true;
            if (service.checkCheckIn(Integer.parseInt(body.get("staff_id").toString()), body.get("check_in_day").toString())) {
                type = false;
            }

            Date date_check_in_day = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("check_in_day").toString());
            Date date_check_in_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.get("check_in_at").toString());

            check.setStaffId(Integer.parseInt(body.get("staff_id").toString()));
            check.setStaffCode(body.get("staff_code").toString());
            check.setCheckInDay(date_check_in_day);
            check.setCheckInAt(date_check_in_at);
            check.setType(type);
            check.setImage(body.get("image").toString());

            service.save(check);

            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Success");
        } catch (ParseException ex) {
            Logger.getLogger(CheckInOutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", "Error");
    }

    @PostMapping(path = "/get-staff-time")
    public ResponseEntity<Object> getStaffTime(@RequestBody HashMap<String, Object> body) {
        ArrayList abc = service.getStaffTime(Integer.parseInt(body.get("staff_id").toString()), body.get("y_m").toString());
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", abc);
    }
    
    @GetMapping(path = "/get-staff-time-get")
    public ResponseEntity<Object> getStaffTimeGet(@RequestParam Integer staff_id, @RequestParam String y_m) {
        ArrayList abc = service.getStaffTime(staff_id, y_m);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", abc);
    }
}
