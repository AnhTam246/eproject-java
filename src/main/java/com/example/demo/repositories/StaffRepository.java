/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;

import com.example.demo.entities.Staff;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nguyenngocanhtam
 */
public interface StaffRepository extends CrudRepository<Staff, Integer> {

    @Query(value = "SELECT * FROM staff WHERE firstname like ?1 and lastname like ?2", nativeQuery = true)
    List<Staff> findByLikeName(String firstname, String lastname);

    @Query(value = "SELECT * FROM staff WHERE status=0", nativeQuery = true)
    List<Staff> findAlls();

    @Query(value = "CALL GetStaffMonth();", nativeQuery = true)
    ArrayList GetStaffMonth();

    @Query(value = "CALL GetStaffOffDateMonth();", nativeQuery = true)
    ArrayList GetStaffOffDateMonth();

    @Query(value = "CALL GetTN();", nativeQuery = true)
    ArrayList GetTN();

    @Query(value = "CALL GetProfile(?1);", nativeQuery = true)
    Map<String, Object> GetProfile(Integer staff_id);

    @Transactional
    @Modifying
    @Query(value = "update Staff c set c.status = :status where c.id = :id")
    void setDelStaff(@Param("id") Integer id, @Param("status") boolean status);

    @Query(value = "SELECT * FROM Staff WHERE status =1", nativeQuery = true)
    List<Staff> ListUndo();

    @Query(value = "SELECT * FROM Staff WHERE id = ?1 and password = ?2", nativeQuery = true)
    Staff checkOldPass(int id, String password);

    @Query(value = "SELECT * FROM staff WHERE status = 0 AND department = ?1", nativeQuery = true)
    List<Staff> findStaffDepartment(int department);
    
    @Query(value = "SELECT * FROM staff WHERE status = 0 AND email = ?1 AND id <> ?2", nativeQuery = true)
    Staff checkEmail(String email, int id);
    
    @Query(value = "SELECT * FROM staff WHERE status = 0 AND id_number = ?1 AND id <> ?2", nativeQuery = true)
    Staff checkIdNumber(String id_number, int id);
}
