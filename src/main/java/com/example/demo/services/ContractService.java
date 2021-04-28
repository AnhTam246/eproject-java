/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Contract;
import com.example.demo.repositories.IContractRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tonamson
 */
@Service
public class ContractService implements IContractService {

    @Autowired
    private IContractRepository contractRepository;

    @Override
    public Contract findById(int id) {
        return contractRepository.findById(id);
    }

    @Override
    public Contract save(Contract item) {
        return contractRepository.save(item);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }
    
    @Override
    public void deleteContract(int id) {
        contractRepository.deleteContract(id);
    }

    @Override
    public List<Contract> listContractByStaffId(int staff_id) {
        return contractRepository.findByStaffIdIs(staff_id);
    }

    @Override
    public Contract findOneByStaffId(int staff_id, String date) {
        return contractRepository.findOneByStaffId(staff_id, date);
    }

    @Override
    public Contract findOneByStaffId(int staff_id) {
        return contractRepository.findOneByStaffId(staff_id);
    }

}
