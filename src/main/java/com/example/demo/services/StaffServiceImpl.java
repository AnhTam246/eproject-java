/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entities.Staff;
import com.example.demo.repositories.StaffRepository;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author nguyenngocanhtam
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository repository;

    @Autowired
    EntityManagerFactory emf;

    @Override
    public List<Staff> findAll() {
        return (List<Staff>) repository.findAlls();
    }

    @Override
    public Staff findOne(int id) {
        Optional<Staff> staffReponse = repository.findById(id);
        Staff staff = staffReponse.get();
        return staff;
    }

    @Override
    public List<Staff> findStaffDepartment() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT concat(s.firstname , ' ', s.lastname), d.name, s.isManager, s.id "
                + "FROM Staff AS s "
                + "INNER JOIN Department AS d ON s.department = d.id");
        List<Staff> list = (List<Staff>) query.getResultList();
        em.close();
        return list;
    }

    @Override
    public ArrayList<Map<String, Object>> findOneStaffDepartment(int id) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT s.code, concat(s.firstname , ' ', s.lastname), d.nameVn "
                + "FROM Staff AS s "
                + "INNER JOIN Department AS d ON s.department = d.id "
                + "WHERE s.id = ?1").setParameter(1, id);
        ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) query.getResultList();
        em.close();
        return list;
    }

    @Override
    public List<Staff> search(String firstname, String lastname) {
        return repository.findByLikeName("%" + firstname + "%", "%" + lastname + "%");
    }

    @Override
    public void editStaff(Staff staff) {
        repository.save(staff);
    }

    @Override
    public void createStaff(Staff staff) {
        repository.save(staff);
    }

    public void deleteStaff(Staff staff) {
        repository.delete(staff);
    }

    @Override
    public void deleteStaff(int id) {
//        repository.delete(id);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList getStaffMonth() {
        return repository.GetStaffMonth();
    }

    @Override
    public ArrayList getStaffOffDateMonth() {
        return repository.GetStaffOffDateMonth();
    }

    @Override
    public ArrayList getTN() {
        return repository.GetTN();
    }

    @Override
    public void updateDateOfLeave() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Staff SET day_of_leave = day_of_leave + 1 WHERE off_date IS NULL and DATEDIFF(DATE_FORMAT(NOW(), '%Y-%m-%d'), joined_at) > 30");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public void resetDateOfLeave() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Staff SET day_of_leave = 0");
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public Map<String, Object> getProfile(int staff_id) {
        return repository.GetProfile(staff_id);
    }

    @Override
    public void setDelStaff(int id, boolean status) {
        repository.setDelStaff(id, status);
    }

    @Override
    public List<Staff> findUndo() {
        return (List<Staff>) repository.ListUndo();
    }

    @Override
    public void updateDepartment(int department, int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Staff SET department = ?1 WHERE id = ?2").setParameter(1, department).setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public boolean checkOldPass(int id, String password) {
        if (repository.checkOldPass(id, password) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updatePassword(int id, String new_pass) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE Staff SET password = ?1 WHERE id = ?2").setParameter(1, new_pass).setParameter(2, id);
        query.executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public List<Staff> findStaffDepartment(int department) {
        return repository.findStaffDepartment(department);
    }

    @Override
    public Staff save(Staff staff) {
        return repository.save(staff);
    }

    @Override
    public boolean checkEmail(String email, int id) {
        Staff staff = repository.checkEmail(email, id);
        if(staff == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIdNumber(String id_number, int id) {
        Staff staff = repository.checkIdNumber(id_number, id);
        if(staff == null) {
            return true;
        }
        return false;
    }

}
