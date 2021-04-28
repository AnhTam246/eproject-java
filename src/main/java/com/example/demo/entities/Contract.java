/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "contract")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contract.findAll", query = "SELECT c FROM Contract c"),
    @NamedQuery(name = "Contract.findById", query = "SELECT c FROM Contract c WHERE c.id = :id"),
    @NamedQuery(name = "Contract.findByStaffId", query = "SELECT c FROM Contract c WHERE c.staffId = :staffId"),
    @NamedQuery(name = "Contract.findByStartDate", query = "SELECT c FROM Contract c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Contract.findByEndDate", query = "SELECT c FROM Contract c WHERE c.endDate = :endDate"),
    @NamedQuery(name = "Contract.findByCreateAt", query = "SELECT c FROM Contract c WHERE c.createAt = :createAt")})
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)

    @NotNull
    @Column(name = "staff_id")
    private int staffId;

    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date startDate;

    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date endDate;

    @NotNull
    @Column(name = "stop_date")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date stopDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "base_salary")
    private double baseSalary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @ManyToOne
    @JoinColumn(name = "staff_id", insertable = false, updatable = false)
    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Contract() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
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
        if (!(object instanceof Contract)) {
            return false;
        }
        Contract other = (Contract) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.Contract[ id=" + id + " ]";
    }

}
