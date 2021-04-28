/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Education;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.EducationService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/education")
public class EducationController {

    @Autowired
    private EducationService service;

    private EducationController(EducationService service) {
        this.service = service;
    }

    @GetMapping(path = "/getStaffOffDateMonth")
    public ResponseEntity<Object> getStaffOffDateMonth() {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.getCountLevelEducation());
    }

    @GetMapping(path = "/get-education-by-staff-id")
    public ResponseEntity<Object> getEducationByStaffId(@RequestParam Integer staff_id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.findByStaffId(staff_id));
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Object> list() {
        List<Education> list = service.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addEducation(@RequestBody HashMap<String, Object> body) {
        try {
            int staff = Integer.parseInt(body.get("staffId").toString());
            int lev = Integer.parseInt(body.get("level").toString());
            int gradua = Integer.parseInt(body.get("graduatedYear").toString());

            Education education = new Education();
            education.setStaffId(staff);
            education.setLevel(lev);
            education.setLevelName(body.get("levelName").toString());
            education.setSchool(body.get("school").toString());
            education.setFieldOfStudy(body.get("fieldOfStudy").toString());
            education.setGraduatedYear(gradua);
            education.setGrade(body.get("grade").toString());
            education.setModeOfStudy(body.get("modeOfStudy").toString());

            service.createEducaion(education);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", e.toString());
        }
    }

    @GetMapping(path = "/one")
    public ResponseEntity<Object> one(@RequestParam int id) {
        Education education = service.findOne(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", education);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Object> updateDepartment(@RequestBody Education education) {
        try {
            service.editEducaion(education);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Lưu thành công", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Save fail", e.getMessage());
        }
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<Object> deleteEducation(@RequestBody HashMap<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        // Education time_leave_delete = service.findOne(id);
        service.deleteEducation(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", "Delete success");
    }
    
    @GetMapping(path = "/check-education")
    public ResponseEntity<Object> checkEducation(@RequestParam String school, @RequestParam String field_of_study, @RequestParam int staff_id, @RequestParam int id) {
        List<Education> list = service.checkEducation(school, field_of_study, staff_id, id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }
}
