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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "kpi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kpi.findAll", query = "SELECT k FROM Kpi k"),
    @NamedQuery(name = "Kpi.findById", query = "SELECT k FROM Kpi k WHERE k.id = :id"),
    @NamedQuery(name = "Kpi.findByStaffId", query = "SELECT k FROM Kpi k WHERE k.staffId = :staffId"),
    @NamedQuery(name = "Kpi.findByDepartmentId", query = "SELECT k FROM Kpi k WHERE k.departmentId = :departmentId"),
    @NamedQuery(name = "Kpi.findByKpiName", query = "SELECT k FROM Kpi k WHERE k.kpiName = :kpiName"),
    @NamedQuery(name = "Kpi.findByCreatedAt", query = "SELECT k FROM Kpi k WHERE k.createdAt = :createdAt"),
    @NamedQuery(name = "Kpi.findByUpdateAt", query = "SELECT k FROM Kpi k WHERE k.updateAt = :updateAt"),
    @NamedQuery(name = "Kpi.findByIsApproved", query = "SELECT k FROM Kpi k WHERE k.isApproved = :isApproved"),
    @NamedQuery(name = "Kpi.findByApprovedBy", query = "SELECT k FROM Kpi k WHERE k.approvedBy = :approvedBy"),
    @NamedQuery(name = "Kpi.findByDel", query = "SELECT k FROM Kpi k WHERE k.del = :del")})
public class Kpi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "department_id")
    private Integer departmentId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "kpi_name")
    private String kpiName;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @Size(max = 1)
    @Column(name = "is_approved")
    private String isApproved;
    @Column(name = "approved_by")
    private Integer approvedBy;
    @Column(name = "del")
    private Boolean del;

    public Kpi() {
    }

    public Kpi(Integer id) {
        this.id = id;
    }

    public Kpi(Integer id, String kpiName) {
        this.id = id;
        this.kpiName = kpiName;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(String isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Integer approvedBy) {
        this.approvedBy = approvedBy;
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
        if (!(object instanceof Kpi)) {
            return false;
        }
        Kpi other = (Kpi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.Kpi[ id=" + id + " ]";
    }
    
}
