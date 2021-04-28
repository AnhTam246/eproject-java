/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Transfer;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.StaffService;
import com.example.demo.services.TransferService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
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
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService service;
    private StaffService staffService;

    private TransferController(TransferService service, StaffService staffService) {
        this.service = service;
        this.staffService = staffService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<Object> list(@RequestParam String day_get, @RequestParam int department) {
        try {
            Date year = new SimpleDateFormat("yyyy-MM-dd").parse(day_get);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", service.getListTransfer(year, department));
        } catch (ParseException ex) {
            Logger.getLogger(SpecialDateController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get data error");
    }

    @PostMapping(path = "/check")
    public ResponseEntity<Object> checkTransfer(@RequestBody HashMap<String, Object> body) {
        try {
            int staff_id = Integer.parseInt(body.get("staff_id").toString());
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", service.getTransferCheck(staff_id));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", e.toString());
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Object> create(@RequestBody HashMap<String, Object> body) {
        try {
            Date created_at = new SimpleDateFormat("yyyy-MM-dd").parse(body.get("created_at").toString());

            Transfer transfer = new Transfer();
            transfer.setStaffId(Integer.parseInt(body.get("staff_id").toString()));
            transfer.setNewDepartment(Integer.parseInt(body.get("new_department").toString()));
            transfer.setOldDepartment(Integer.parseInt(body.get("old_department").toString()));
            transfer.setOldManagerApproved(false);
            transfer.setNewManagerApproved(false);
            transfer.setManagerApproved(false);
            transfer.setHrApproved(Integer.parseInt(body.get("hr_approved").toString()));
            transfer.setNewSalary(Double.parseDouble(body.get("new_salary").toString()));  
            transfer.setCreatedBy(Integer.parseInt(body.get("created_by").toString()));
            transfer.setNote(body.get("note").toString());
//            transfer.setNoteManager(body.get("note_manager").toString());
            transfer.setCreatedAt(created_at);
            transfer.setDel(false);

            service.createTransfer(transfer);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save success", "Add transfer Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save fail", e.toString());
        }
    }

    @PostMapping(path = "/delete")
    public ResponseEntity<Object> delete(@RequestBody HashMap<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        service.deleteTransfer(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Delete Transfer success", "Delete success");
    }

    @GetMapping(path = "/detail")
    public ResponseEntity<Object> detail(@RequestParam int id) {
        Optional<Transfer> transfer = service.detailTransfer(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", transfer);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Object> update(@RequestBody HashMap<String, Object> body) {
        try {
            String note_manager = "";
            String note = "";
            if(body.get("note_manager") != null){
                note_manager = body.get("note_manager").toString();
            }
            if(body.get("note") != null){
                note = body.get("note").toString();
            }
            service.updateTransfer(
                    Integer.parseInt(body.get("new_department").toString())
                    ,note
                    ,Integer.parseInt(body.get("hr_approved").toString())
                    ,Double.parseDouble(body.get("new_salary").toString())
                    ,note_manager
                    ,Integer.parseInt(body.get("id").toString()));
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Update fail", e);
        }
    }

    @GetMapping(path = "/approve")
    public ResponseEntity<Object> approve(@RequestParam int id, @RequestParam int department) {
        Optional<Transfer> transfer = service.detailTransfer(id);

        if (transfer.get().getNewDepartment() == department) {
            service.newManagerApprove(id);
        } else if (department == 5) {
            service.managerApprove(id);
                staffService.updateDepartment(transfer.get().getNewDepartment(), transfer.get().getStaffId());
                return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve manager", "Staff changed department");
            
        } else {
            service.oldManagerApprove(id);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve Success", "Approve Success");
    }
    
    @GetMapping(path = "/getdelete")
    public ResponseEntity<Object> deleteTransfer(@RequestParam int id) {
        try {
            service.setDelTransfer(id, true);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Xóa thành công", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), false);
        }

    }

    
}
