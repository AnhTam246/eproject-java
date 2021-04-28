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
@Table(name = "special_date")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SpecialDate.findAll", query = "SELECT s FROM SpecialDate s"),
    @NamedQuery(name = "SpecialDate.findById", query = "SELECT s FROM SpecialDate s WHERE s.id = :id"),
    @NamedQuery(name = "SpecialDate.findByDaySpecialFrom", query = "SELECT s FROM SpecialDate s WHERE s.daySpecialFrom = :daySpecialFrom"),
    @NamedQuery(name = "SpecialDate.findByDaySpecialTo", query = "SELECT s FROM SpecialDate s WHERE s.daySpecialTo = :daySpecialTo"),
    @NamedQuery(name = "SpecialDate.findByNote", query = "SELECT s FROM SpecialDate s WHERE s.note = :note"),
    @NamedQuery(name = "SpecialDate.findByTypeDay", query = "SELECT s FROM SpecialDate s WHERE s.typeDay = :typeDay"),
    @NamedQuery(name = "SpecialDate.findByStaffRequest", query = "SELECT s FROM SpecialDate s WHERE s.staffRequest = :staffRequest"),
    @NamedQuery(name = "SpecialDate.findByDepartmentRequest", query = "SELECT s FROM SpecialDate s WHERE s.departmentRequest = :departmentRequest"),
    @NamedQuery(name = "SpecialDate.findByIsApproved", query = "SELECT s FROM SpecialDate s WHERE s.isApproved = :isApproved"),
    @NamedQuery(name = "SpecialDate.findByStaffOt", query = "SELECT s FROM SpecialDate s WHERE s.staffOt = :staffOt")})
public class SpecialDate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_special_from")
    @Temporal(TemporalType.DATE)
    private Date daySpecialFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_special_to")
    @Temporal(TemporalType.DATE)
    private Date daySpecialTo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type_day")
    private int typeDay;
    @Column(name = "staff_request")
    private Integer staffRequest;
    @Column(name = "department_request")
    private Integer departmentRequest;
    @Column(name = "is_approved")
    private Integer isApproved;
    @Size(max = 255)
    @Column(name = "staff_ot")
    private String staffOt;

    public SpecialDate() {
    }

    public SpecialDate(Integer id) {
        this.id = id;
    }

    public SpecialDate(Integer id, Date daySpecialFrom, Date daySpecialTo, String note, int typeDay) {
        this.id = id;
        this.daySpecialFrom = daySpecialFrom;
        this.daySpecialTo = daySpecialTo;
        this.note = note;
        this.typeDay = typeDay;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDaySpecialFrom() {
        return daySpecialFrom;
    }

    public void setDaySpecialFrom(Date daySpecialFrom) {
        this.daySpecialFrom = daySpecialFrom;
    }

    public Date getDaySpecialTo() {
        return daySpecialTo;
    }

    public void setDaySpecialTo(Date daySpecialTo) {
        this.daySpecialTo = daySpecialTo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getTypeDay() {
        return typeDay;
    }

    public void setTypeDay(int typeDay) {
        this.typeDay = typeDay;
    }

    public Integer getStaffRequest() {
        return staffRequest;
    }

    public void setStaffRequest(Integer staffRequest) {
        this.staffRequest = staffRequest;
    }

    public Integer getDepartmentRequest() {
        return departmentRequest;
    }

    public void setDepartmentRequest(Integer departmentRequest) {
        this.departmentRequest = departmentRequest;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public String getStaffOt() {
        return staffOt;
    }

    public void setStaffOt(String staffOt) {
        this.staffOt = staffOt;
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
        if (!(object instanceof SpecialDate)) {
            return false;
        }
        SpecialDate other = (SpecialDate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.SpecialDate[ id=" + id + " ]";
    }
    
}
