/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.entities.Kpi;
import com.example.demo.entities.KpiDetail;
import com.example.demo.entities.KpiDetailChild;
import com.example.demo.helpers.ResponseHandler;
import com.example.demo.services.KpiDetailChildService;
import com.example.demo.services.KpiDetailService;
import com.example.demo.services.KpiService;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
@RequestMapping("/kpi")
public class KpiController {
    @Autowired
    private KpiService service;
    private KpiDetailService detailService;
    private KpiDetailChildService childService;

    private KpiController(KpiService service, KpiDetailService detailService, KpiDetailChildService childService) {
        this.service = service;
        this.detailService = detailService;
        this.childService = childService;
    }
    
    @GetMapping(path = "/find-kpi-staff")
    public ResponseEntity<Object> findKpiStaff(@RequestParam int staff_id, @RequestParam String kpi_name) {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", service.findKpiStaff(staff_id, kpi_name));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get data error");
        }
    }
    
    @GetMapping(path = "/find-kpi-department")
    public ResponseEntity<Object> findKpiDepartment(@RequestParam int department_id, @RequestParam String kpi_name) {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", service.findKpiDepartment(department_id, kpi_name));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", "Get data error");
        }
    }
    
    @PostMapping(path = "/save-kpi")
    public ResponseEntity<Object> saveKpi(@RequestBody HashMap<String,Object> body) {
        try {
            //Save KPI
            String kpi_name = body.get("kpi_name").toString();
            Date created_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.get("created_at").toString());
           
            Kpi kpi = new Kpi();
            //Kpi of staff
            if(body.get("department_id") == null) {
                kpi.setDepartmentId(null);
            } else {
                kpi.setDepartmentId(Integer.parseInt(body.get("department_id").toString()));
            }
            
            //Kpi of department
            if(body.get("staff_id") == null) {
                kpi.setStaffId(null);
            } else {
                kpi.setStaffId(Integer.parseInt(body.get("staff_id").toString()));
            }
            
            //set approve if manager create
            if(body.get("approved_by") == null) {
                kpi.setApprovedBy(null);
            } else {
                kpi.setApprovedBy(Integer.parseInt(body.get("approved_by").toString()));
            }

            kpi.setKpiName(kpi_name);
            kpi.setIsApproved(body.get("is_approved").toString());
            kpi.setDel(false);
            kpi.setCreatedAt(created_at);

            Kpi new_kpi = service.saveKpi(kpi);

            //Save Kpi Details
            ArrayList<HashMap> tasks = (ArrayList) body.get("tasks");
            
            for(int i=0; i < tasks.size(); i++){
                KpiDetail kpiDetail = new KpiDetail();
                //Create kpi detail with new kpi
                kpiDetail.setKpiId(new_kpi.getId());
                kpiDetail.setTaskTarget(tasks.get(i).get("target").toString());
//                kpiDetail.setTaskDescription(tasks.get(i).get("task_description").toString());
//                kpiDetail.setDutiesActivities(tasks.get(i).get("duties_activities").toString());
//                kpiDetail.setSkill(tasks.get(i).get("skill").toString());
                kpiDetail.setRatio(Integer.parseInt(tasks.get(i).get("ratio").toString()));
                kpiDetail.setDel(false);
                
                try {
                    detailService.saveKpiDetail(kpiDetail);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save kpi, kpi details success", new_kpi);
        } catch (Exception ex) {
             Logger.getLogger(KpiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save kpi fail", "Error");
    }
    
    @PostMapping(path = "/update-kpi-details")
    public ResponseEntity<Object> updateKpiDetails(@RequestBody HashMap<String,Object> body) {
        try {
            //Save Kpi Details
            ArrayList<HashMap> tasks = (ArrayList) body.get("tasks");
            
            for(int i=0; i < tasks.size(); i++){
                KpiDetail kpiDetail = new KpiDetail();
                //Create kpi detail with new kpi
                if(tasks.get(i).get("id") != null) {
                   kpiDetail.setId(Integer.parseInt(tasks.get(i).get("id").toString()));
                }
                kpiDetail.setKpiId(Integer.parseInt(body.get("kpi_id").toString()));
                kpiDetail.setTaskTarget(tasks.get(i).get("target").toString());
//                kpiDetail.setTaskDescription(tasks.get(i).get("task_description").toString());
//                kpiDetail.setDutiesActivities(tasks.get(i).get("duties_activities").toString());
//                kpiDetail.setSkill(tasks.get(i).get("skill").toString());
                kpiDetail.setRatio(Integer.parseInt(tasks.get(i).get("ratio").toString()));
                kpiDetail.setDel(Boolean.parseBoolean(tasks.get(i).get("del").toString()));
                
                try {
                    detailService.saveKpiDetail(kpiDetail);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
            Optional<Kpi> kpi_update = service.findOne(Integer.parseInt(body.get("kpi_id").toString()));
            
            String is_approved = body.get("is_approved").toString();
            int id = Integer.parseInt(body.get("kpi_id").toString());
            service.approveKpi(is_approved, id, 0);
            
            Date update_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.get("update_at").toString()); 
            service.updateAt(update_at, id);
            
            if(body.get("approved_by") != null) {
                service.approvedBy(Integer.parseInt(body.get("approved_by").toString()), id);
            }
            
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save kpi, kpi details success", kpi_update);
        } catch (Exception ex) {
             Logger.getLogger(KpiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save kpi fail", "Error");
    }
    
    @GetMapping(path = "/get-list-kpi-staff")
    public ResponseEntity<Object> getListKpiStaff(@RequestParam int department, @RequestParam int is_manager) {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", service.getKpiStaff(department, is_manager));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", e);
        }
    }
    
    @GetMapping(path = "/get-list-kpi-department")
    public ResponseEntity<Object> getListKpiDepartment(@RequestParam int department, @RequestParam int is_manager) {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", service.getKpiDepartment(department, is_manager));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", e);
        }
    }
    
    @PostMapping(path = "/approve-kpi")
    public ResponseEntity<Object> approveKpi(@RequestBody HashMap<String,Object> body) {
        try {   
            String is_approved = body.get("is_approved").toString();
            int approved_by = Integer.parseInt(body.get("approved_by").toString());
            int id = Integer.parseInt(body.get("id").toString());
            service.approveKpi(is_approved, id, approved_by);
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve success", "Success");
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Approve fail", e);
        }
    }
    
    @GetMapping(path = "/get-detail-of-kpi")
    public ResponseEntity<Object> getDetailOfKpi(@RequestParam int kpi_id) {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", service.getDetailOfKpi(kpi_id));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Fail", e.toString());
        }
    }
    
    @GetMapping(path = "/get-kpi-detail-child")
    public ResponseEntity<Object> getKpiDetailChild(@RequestParam int kpi_detail_id) {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get data success", childService.getKpiDetailChild(kpi_detail_id));  
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Get Fail", e.toString());
        }
    }
    
    @PostMapping(path = "/save-detail-child")
    public ResponseEntity<Object> saveDetailChild(@RequestBody HashMap<String,Object> body) {
        try {
            //Save Kpi Details
            ArrayList<HashMap> tasks = (ArrayList) body.get("tasks");
            
            childService.clearAllDetailChild(Integer.parseInt(body.get("id_kpi_detail").toString()));
            
            for(int i=0; i < tasks.size(); i++){
                KpiDetailChild kpiDetailChild = new KpiDetailChild();
                //Create kpi detail with new kpi
                if(tasks.get(i).get("id") != null) {
                    kpiDetailChild.setId(Integer.parseInt(tasks.get(i).get("id").toString()));
                }
                kpiDetailChild.setKpiDetailId(Integer.parseInt(body.get("id_kpi_detail").toString()));
                kpiDetailChild.setName(tasks.get(i).get("name").toString());
                kpiDetailChild.setNumberTarget(Integer.parseInt(tasks.get(i).get("number_target").toString()));
                kpiDetailChild.setNumberGet(Integer.parseInt(tasks.get(i).get("number_get").toString()));
                kpiDetailChild.setDutiesActivities(tasks.get(i).get("duties_activities").toString());
                kpiDetailChild.setName(tasks.get(i).get("name").toString());
                kpiDetailChild.setSkill(tasks.get(i).get("skill").toString());
                
                try {
                    childService.saveKpiDetailChild(kpiDetailChild);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            
            return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save kpi, kpi details success", "Success");
        } catch (Exception ex) {
             Logger.getLogger(KpiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseHandler.generateResponse(HttpStatus.OK, true, "Save kpi fail", "Error");
    }
}
