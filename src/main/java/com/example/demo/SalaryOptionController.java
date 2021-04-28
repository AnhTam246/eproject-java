/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.SalaryOption;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.SalaryOptionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tonamson
 */

@RestController
@RequestMapping("/salary-option")
public class SalaryOptionController {
    
    @Autowired
    private SalaryOptionService salaryOptionService;
    
    
    @GetMapping(path = "/list")
    public ResponseEntity<Object> listOption() {
        List<SalaryOption> data = salaryOptionService.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Gửi yêu cầu thành công", data);
    }
    
    @GetMapping(path = "/list-by-type")
    public ResponseEntity<Object> listOptionByType(@RequestParam String type) {
        List<SalaryOption> data = salaryOptionService.findByType(type);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Gửi yêu cầu thành công", data);
    }
}
