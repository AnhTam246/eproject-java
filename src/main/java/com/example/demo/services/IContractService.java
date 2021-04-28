/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Contract;
import java.util.List;

/**
 *
 * @author tonamson
 */
public interface IContractService {

    List<Contract> findAll();

    List<Contract> listContractByStaffId(int staff_id);

    Contract findById(int id);

    Contract findOneByStaffId(int staff_id, String date);

    Contract save(Contract item);
    
    Contract findOneByStaffId(int staff_id);

    void deleteContract(int id);

}
