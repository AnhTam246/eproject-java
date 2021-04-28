/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Regional;
import com.example.demo.repositories.RegionalRepository;
import com.example.demo.repositories.StaffRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class RegionalImpl implements RegionalService{

    
    @Autowired
    private RegionalRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<Regional> findAll() {
         return (List<Regional>) repository.ListAll();
    }
    
    @Override
    public List<Regional> findDistrict(int parent) {
         return (List<Regional>) repository.ListDistrict(parent);
    }

    @Override
    public Optional<Regional> findOne(int id) {
        return repository.findById(id);
    }
    
}
