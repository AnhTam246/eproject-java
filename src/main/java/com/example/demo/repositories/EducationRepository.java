/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Education;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Red
 */
public interface EducationRepository extends CrudRepository<Education, Integer> {
    @Query(value = "CALL GetCountLevelEducation();", nativeQuery = true)
    ArrayList getCountLevelEducation();
    @Query(value = "SELECT * FROM education WHERE staff_id = ?1", nativeQuery=true)
    List<Education> findByStaffId(int staff_id);
    @Query(value = "SELECT * FROM education WHERE school = ?1 AND field_of_study = ?2 AND staff_id = ?3 AND id <> ?4", nativeQuery=true)
    List<Education> checkEducation(String school, String field_of_study, int staff_id, int id);
}
