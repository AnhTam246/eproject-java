/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import com.example.demo.entities.Regional;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author DELL
 */
public interface RegionalService {
    List<Regional> findAll();
    List<Regional> findDistrict(int parent);
    Optional<Regional> findOne(int id);
}
