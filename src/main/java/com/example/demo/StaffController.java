/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Staff;
import com.example.demo.helpers.ResponseHandler;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.StaffService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author nguyenngocanhtam
 */
@RestController // This means that this class is a Controller
@RequestMapping(path = "/staff") // This means URL's start with /demo (after Application path)
public class StaffController {

    @Autowired
    private StaffService service;

    private StaffController(StaffService service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Object> list() {
        List<Staff> list = service.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @GetMapping(path = "/one")
    public ResponseEntity<Object> one(@RequestParam int id) {
        Staff staff = service.findOne(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", staff);
    }

    @GetMapping(path = "/findStaffDepartment")
    public ResponseEntity<Object> findStaffDepartment() {
        List<Staff> list = service.findStaffDepartment();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @GetMapping(path = "/findOneStaffDepartment")
    public ResponseEntity<Object> findOneStaffDepartment(@RequestParam int id) {
        ArrayList<Map<String, Object>> list = service.findOneStaffDepartment(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<Object> search(@RequestParam String firstname, @RequestParam String lastname) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.search(firstname, lastname));
    }

    @GetMapping(path = "/getStaffMonth")
    public ResponseEntity<Object> getStaffMonth() {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.getStaffMonth());
    }

    @GetMapping(path = "/getStaffOffDateMonth")
    public ResponseEntity<Object> getStaffOffDateMonth() {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.getStaffOffDateMonth());
    }

    @GetMapping(path = "/getTN")
    public ResponseEntity<Object> getTN() {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.getTN());
    }

    @GetMapping(path = "/updateDayOfLeave")
    public ResponseEntity<Object> updateDayOfLeave() {
        service.updateDateOfLeave();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", "Success");
    }

    @GetMapping(path = "/resetDayOfLeave")
    public ResponseEntity<Object> resetDayOfLeave() {
        service.resetDateOfLeave();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", "Success");
    }

    /*@RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        Staff c = service.findOne(id);
        if (c != null) {
            service.deleteStaff(c);
            model.addAttribute("list", service.findAll());
        }
        return "redirect:/";
    }*/
    @PostMapping(path = "/add")
    public ResponseEntity<Object> addStaff(@RequestBody Staff staff) {
        try {
            Staff staff_data = service.save(staff);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", staff_data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Save fail", e.getMessage());
        }
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Object> updateDepartment(@RequestBody Staff staff) {
        try {
            if(service.checkEmail(staff.getEmail(), staff.getId()) == false) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Duplicate email", true);
            }
            
            if(service.checkIdNumber(staff.getIdNumber(), staff.getId()) == false) {
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Duplicate id number", true);
            }
            service.editStaff(staff);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, "Save fail", e.getMessage());
        }
    }

    @GetMapping(path = "/get-profile")
    public ResponseEntity<Object> search(@RequestParam Integer staff_id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", service.getProfile(staff_id));
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<Object> deleteStaff(@RequestParam int id) {
        try {
            service.setDelStaff(id, true);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Xóa thành công", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), false);
        }

    }

    @GetMapping(path = "/undo")
    public ResponseEntity<Object> undoStaff(@RequestParam int id) {
        try {
            service.setDelStaff(id, false);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Hoàn tác thành công", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), false);
        }
    }

    @GetMapping(path = "/listUndo")
    public ResponseEntity<Object> listUndo() {
        List<Staff> list = service.findUndo();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }

    @PostMapping(path = "/change-password")
    public ResponseEntity<Object> changePassword(@RequestBody HashMap<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String pass_old = body.get("pass_old").toString();
        String pass_new = body.get("pass_new").toString();

        if (service.checkOldPass(id, pass_old)) {
            service.updatePassword(id, pass_new);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success", "Change password Success");
        }

        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Wrong old pass");
    }
    
    @PostMapping(path = "/change-pass-forgot")
    public ResponseEntity<Object> changePasswordForgot(@RequestBody HashMap<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String pass_new = body.get("pass_new").toString();

        service.updatePassword(id, pass_new);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Success", "Change password Success");
    }

    @GetMapping(path = "/find-staff-department")
    public ResponseEntity<Object> findStaffDepartment(@RequestParam int department) {
        List<Staff> list = service.findStaffDepartment(department);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Request success", list);
    }
}
