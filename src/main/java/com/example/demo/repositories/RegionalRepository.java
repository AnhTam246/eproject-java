/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Regional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author DELL
 */
public interface RegionalRepository extends CrudRepository<Regional, Integer>{
    
    @Query(value = "SELECT * FROM Regional WHERE parent =0", nativeQuery=true)
    List<Regional> ListAll();
    @Query(value = "SELECT * FROM Regional WHERE parent = ?1", nativeQuery=true)
    List<Regional> ListDistrict(Integer parent_id);
}
