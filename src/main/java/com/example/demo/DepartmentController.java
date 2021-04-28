/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Department;
import com.example.demo.services.DepartmentService;

import com.example.demo.helpers.ResponseHandler;
import java.util.HashMap;
import java.util.List;
import static org.apache.tomcat.jni.Lock.name;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DELL
 */
@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

//    @RequestMapping("/url")
//    public String page(Model model) {
//        model.addAttribute("attribute", "value");
//        return "view.name";
//    }
//    
    private DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Object> list() {
        List<Department> list = service.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @GetMapping(path = "/listUndo")
    public ResponseEntity<Object> listUndo() {
        List<Department> list = service.findUndo();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @GetMapping(path = "/detail")
    public ResponseEntity<Object> findDepartment(@RequestParam int id) {
        Department department = service.findOne(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", department);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Object> addDepartment(@RequestBody HashMap<String, Object> body) {
        try {
            Department department = new Department();
            department.setName(body.get("name").toString());
            department.setNameVn(body.get("nameVn").toString());
            department.setDel(false);
            service.createDepartment(department);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", e.toString());
        }
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Object> updateDepartment(@RequestBody HashMap<String, Object> body) {
        try {
            int idd = Integer.parseInt(body.get("id").toString());
            boolean dee = body.get("del").equals("1");
            Department department = service.findOne(idd);

            department.setId(idd);
            department.setName(body.get("name").toString());
            department.setNameVn(body.get("nameVn").toString());
            department.setDel(dee);
            service.editDepartment(department);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update fail", e);
        }
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<Object> deleteDepartment(@RequestParam int id) {
        try {
            service.setDelDepartment(id, true);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Xóa thành công", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), false);
        }

    }

    @GetMapping(path = "/undo")
    public ResponseEntity<Object> undoDepartment(@RequestParam int id) {
        try {
            service.setDelDepartment(id, false);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Hoàn tác thành công", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), false);
        }
    }
    
    @GetMapping(path = "/check-department")
    public ResponseEntity<Object> checkDepartment(@RequestParam int id, @RequestParam String name, @RequestParam String name_vn) {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success", service.checkDepartment(id, name, name_vn));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), false);
        }
    }
}
