/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Transfer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Red
 */
public interface TransferService {
    ArrayList<Map<String, Object>> getListTransfer(Date month_get, int department);
    Transfer createTransfer(Transfer transfer);
    void deleteTransfer(int id);
    Optional<Transfer> detailTransfer(int id);
    void updateTransfer(int new_department, String note,int hr_approved,double new_salary, String note_manager, int id);
    Transfer getTransferCheck(int staff_id);
    void oldManagerApprove(int id);
    void newManagerApprove(int id);
    void managerApprove(int id);
    
   void setDelTransfer(int id, boolean del);
}
