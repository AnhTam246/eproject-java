/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Contract;
import com.example.demo.helpers.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.services.IContractService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tonamson
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    private IContractService contractService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GetMapping(path = "/list")
    public ResponseEntity<Object> listContract(@RequestParam boolean del) {
        List<Contract> data = contractService.findAll();
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Gửi yêu cầu thành công", data);
    }

    @GetMapping(path = "/by-staff")
    public ResponseEntity<Object> listContractByStaff(@RequestParam int staff_id) {
        List<Contract> data = contractService.listContractByStaffId(staff_id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Gửi yêu cầu thành công", data);
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Object> addNewContract(@RequestBody Map<String, Object> body) {
        try {
            Contract contract = new Contract();
            if (body.containsKey("id")) {
                contract.setId(Integer.parseInt(body.get("id").toString()));
            }

            contract.setStaffId(Integer.parseInt(body.get("staffId").toString()));
            contract.setStartDate(sdf.parse(body.get("startDate").toString()));
            contract.setEndDate(sdf.parse(body.get("endDate").toString()));
            contract.setStopDate(sdf.parse(body.get("stopDate").toString()));
            contract.setBaseSalary(Double.parseDouble(body.get("baseSalary").toString()));
            Contract data = contractService.save(contract);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Lưu thành công", data);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), null);
        }
    }

    @GetMapping(path = "/stop")
    public ResponseEntity<Object> stopContract(@RequestParam int id) {
        try {
            Contract contract = contractService.findById(id);
            contract.setStopDate(new Date());
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Lưu thành công", contractService.save(contract));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), null);
        }
    }

    @GetMapping(path = "/delete")
    public ResponseEntity<Object> deleteContract(@RequestParam int id) {
        try {
            contractService.deleteContract(id);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Xóa thành công", true);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, false, e.getMessage(), false);
        }
    }

    @GetMapping(path = "/detail")
    public ResponseEntity<Object> editContract(@RequestParam int id) {
        Contract contract = contractService.findById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Gửi yêu cầu thành công", contract);
    }

    @GetMapping(path = "/last-contract")
    public ResponseEntity<Object> lastContractByStaffId(@RequestParam int staff_id) {
        Contract contract = contractService.findOneByStaffId(staff_id);
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Gửi yêu cầu thành công", contract);
    }
}
