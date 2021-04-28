/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "transfer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transfer.findAll", query = "SELECT t FROM Transfer t"),
    @NamedQuery(name = "Transfer.findById", query = "SELECT t FROM Transfer t WHERE t.id = :id"),
    @NamedQuery(name = "Transfer.findByStaffId", query = "SELECT t FROM Transfer t WHERE t.staffId = :staffId"),
    @NamedQuery(name = "Transfer.findByNewDepartment", query = "SELECT t FROM Transfer t WHERE t.newDepartment = :newDepartment"),
    @NamedQuery(name = "Transfer.findByOldManagerApproved", query = "SELECT t FROM Transfer t WHERE t.oldManagerApproved = :oldManagerApproved"),
    @NamedQuery(name = "Transfer.findByNewManagerApproved", query = "SELECT t FROM Transfer t WHERE t.newManagerApproved = :newManagerApproved"),
    @NamedQuery(name = "Transfer.findBymanagerApproved", query = "SELECT t FROM Transfer t WHERE t.managerApproved = :managerApproved"),
    @NamedQuery(name = "Transfer.findByCreatedAt", query = "SELECT t FROM Transfer t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Transfer.findByCreatedBy", query = "SELECT t FROM Transfer t WHERE t.createdBy = :createdBy"),
    @NamedQuery(name = "Transfer.findByDel", query = "SELECT t FROM Transfer t WHERE t.del = :del")})
public class Transfer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "old_department")
    private Integer oldDepartment;
    @Column(name = "new_department")
    private Integer newDepartment;
    @Column(name = "old_manager_approved")
    private Boolean oldManagerApproved;
    @Column(name = "new_manager_approved")
    private Boolean newManagerApproved;
    @Column(name = "manager_approved")
    private Boolean managerApproved;
    @Column(name = "hr_approved")
    private Integer hrApproved;
    
    @Column(name = "new_salary")
    private double newSalary;
     
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    
    @Column(name = "update_at")
    @Temporal(TemporalType.DATE)
//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
    private Date updateAt;
     
    @Column(name = "created_by")
    private Integer createdBy;
    @Lob
    @Size(max = 65535)
    @Column(name = "note")
    private String note;
    @Size(max = 65535)
    @Null
    @Column(name = "note_manager")
    private String noteManager;
    @Column(name = "del")
    private Boolean del;

    public Transfer() {
    }

    public Transfer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public void setOldDepartment(Integer oldDepartment) {
        this.oldDepartment = oldDepartment;
    }

    public Integer getOldDepartment() {
        return oldDepartment;
    }

    public Integer getNewDepartment() {
        return newDepartment;
    }

    public void setNewDepartment(Integer newDepartment) {
        this.newDepartment = newDepartment;
    }

    public Boolean getOldManagerApproved() {
        return oldManagerApproved;
    }

    public void setOldManagerApproved(Boolean oldManagerApproved) {
        this.oldManagerApproved = oldManagerApproved;
    }

    public Boolean getNewManagerApproved() {
        return newManagerApproved;
    }

    public void setNewManagerApproved(Boolean newManagerApproved) {
        this.newManagerApproved = newManagerApproved;
    }

    public void setManagerApproved(Boolean managerApproved) {
        this.managerApproved = managerApproved;
    }

    public Boolean getManagerApproved() {
        return managerApproved;
    }

    public void setHrApproved(Integer hrApproved) {
        this.hrApproved = hrApproved;
    }

    public Integer getHrApproved() {
        return hrApproved;
    }

    public void setNewSalary(double newSalary) {
        this.newSalary = newSalary;
    }

    public double getNewSalary() {
        return newSalary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }
   

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setNoteManager(String noteManager) {
        this.noteManager = noteManager;
    }

    public String getNoteManager() {
        return noteManager;
    }
    

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transfer)) {
            return false;
        }
        Transfer other = (Transfer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.Transfer[ id=" + id + " ]";
    }
    
}
