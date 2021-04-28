/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repositories;
import com.example.demo.entities.Department;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author DELL
 */
public interface DepartmentRepository extends CrudRepository<Department, Integer>{
 
     @Query(value = "SELECT * FROM department WHERE name like ?1", nativeQuery=true)
    List<Department> findByLikeName(String firstname);
    
    @Query(value = "SELECT * FROM Department WHERE id = ?1", nativeQuery=true)
    Department findOne(int id);
    
       @Query(value = "SELECT * FROM department WHERE del =0", nativeQuery=true)
    List<Department> ListAll();
    @Query(value = "SELECT * FROM department WHERE del =1", nativeQuery=true)
    List<Department> ListUndo();
    
    @Transactional
    @Modifying
    @Query(value = "update Department c set c.del = :del where c.id = :id")
    void setDelDepartment(@Param("id") Integer id, @Param("del") Boolean del);
   
    @Query(value = "SELECT * FROM department WHERE id <> ?1 AND (`name` = ?2 OR `name` = ?3)", nativeQuery=true)
    List<Department> checkDepartment(int id, String name, String name_vn);
}
