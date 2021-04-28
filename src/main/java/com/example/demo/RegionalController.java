/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Regional;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.RegionalService;
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
 * @author DELL
 */
@RestController // This means that this class is a Controller
@RequestMapping(path = "/regional") // This means URL's start with /demo (after Application path)
public class RegionalController {
    
     @Autowired
    private RegionalService service;

    private RegionalController(RegionalService service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Object> list() {
        List<Regional> list = service.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @GetMapping(path = "/list-district")
    public ResponseEntity<Object> list(@RequestParam int parent) {
        List<Regional> list = service.findDistrict(parent);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }
    
    @GetMapping(path = "/get-one")
    public ResponseEntity<Object> getOne(@RequestParam int id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.findOne(id));
    }
}
