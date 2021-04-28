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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "time_special")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeSpecial.findAll", query = "SELECT t FROM TimeSpecial t"),
    @NamedQuery(name = "TimeSpecial.findById", query = "SELECT t FROM TimeSpecial t WHERE t.id = :id"),
    @NamedQuery(name = "TimeSpecial.findByStaffId", query = "SELECT t FROM TimeSpecial t WHERE t.staffId = :staffId"),
    @NamedQuery(name = "TimeSpecial.findBySpecialDateId", query = "SELECT t FROM TimeSpecial t WHERE t.specialDateId = :specialDateId"),
    @NamedQuery(name = "TimeSpecial.findByDayTimeSpecial", query = "SELECT t FROM TimeSpecial t WHERE t.dayTimeSpecial = :dayTimeSpecial"),
    @NamedQuery(name = "TimeSpecial.findByNumberTime", query = "SELECT t FROM TimeSpecial t WHERE t.numberTime = :numberTime"),
    @NamedQuery(name = "TimeSpecial.findByMultiply", query = "SELECT t FROM TimeSpecial t WHERE t.multiply = :multiply"),
    @NamedQuery(name = "TimeSpecial.findByDateCreate", query = "SELECT t FROM TimeSpecial t WHERE t.dateCreate = :dateCreate")})
public class TimeSpecial implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "special_date_id")
    private int specialDateId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "day_time_special")
    @Temporal(TemporalType.DATE)
    private Date dayTimeSpecial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "number_time")
    private float numberTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "multiply")
    private int multiply;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_create")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;

    public TimeSpecial() {
    }

    public TimeSpecial(Integer id) {
        this.id = id;
    }

    public TimeSpecial(Integer id, int staffId, int specialDateId, Date dayTimeSpecial, float numberTime, int multiply, Date dateCreate) {
        this.id = id;
        this.staffId = staffId;
        this.specialDateId = specialDateId;
        this.dayTimeSpecial = dayTimeSpecial;
        this.numberTime = numberTime;
        this.multiply = multiply;
        this.dateCreate = dateCreate;
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

    public int getSpecialDateId() {
        return specialDateId;
    }

    public void setSpecialDateId(int specialDateId) {
        this.specialDateId = specialDateId;
    }

    public Date getDayTimeSpecial() {
        return dayTimeSpecial;
    }

    public void setDayTimeSpecial(Date dayTimeSpecial) {
        this.dayTimeSpecial = dayTimeSpecial;
    }

    public float getNumberTime() {
        return numberTime;
    }

    public void setNumberTime(float numberTime) {
        this.numberTime = numberTime;
    }

    public int getMultiply() {
        return multiply;
    }

    public void setMultiply(int multiply) {
        this.multiply = multiply;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
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
        if (!(object instanceof TimeSpecial)) {
            return false;
        }
        TimeSpecial other = (TimeSpecial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.TimeSpecial[ id=" + id + " ]";
    }
    
}
