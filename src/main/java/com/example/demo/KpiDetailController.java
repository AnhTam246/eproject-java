/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.KpiDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Red
 */
@RestController
@RequestMapping("/kpi-detail")
public class KpiDetailController {
    @Autowired
    private KpiDetailService service;

    private KpiDetailController(KpiDetailService service) {
        this.service = service;
    }
    
    @GetMapping(path = "/get-kpi-detail")
    public ResponseEntity<Object> getKpiDetailByKpiId(@RequestParam int kpi_id) {
        try {         
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get kpi detail success", service.getKpiDetailByKpiId(kpi_id));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get kpi detail error");
        }
    }
    
    @GetMapping(path = "/get-one-kpi-detail")
    public ResponseEntity<Object> getById(@RequestParam int id) {
        try {         
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get kpi detail success", service.getOne(id));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get kpi detail error");
        }
    }
}
