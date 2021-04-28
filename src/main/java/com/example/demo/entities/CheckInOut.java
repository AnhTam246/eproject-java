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
@Table(name = "check_in_out")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CheckInOut.findAll", query = "SELECT c FROM CheckInOut c"),
    @NamedQuery(name = "CheckInOut.findById", query = "SELECT c FROM CheckInOut c WHERE c.id = :id"),
    @NamedQuery(name = "CheckInOut.findByStaffId", query = "SELECT c FROM CheckInOut c WHERE c.staffId = :staffId"),
    @NamedQuery(name = "CheckInOut.findByStaffCode", query = "SELECT c FROM CheckInOut c WHERE c.staffCode = :staffCode"),
    @NamedQuery(name = "CheckInOut.findByCheckInDay", query = "SELECT c FROM CheckInOut c WHERE c.checkInDay = :checkInDay"),
    @NamedQuery(name = "CheckInOut.findByCheckInAt", query = "SELECT c FROM CheckInOut c WHERE c.checkInAt = :checkInAt"),
    @NamedQuery(name = "CheckInOut.findByType", query = "SELECT c FROM CheckInOut c WHERE c.type = :type"),
    @NamedQuery(name = "CheckInOut.findByImage", query = "SELECT c FROM CheckInOut c WHERE c.image = :image")})
public class CheckInOut implements Serializable {

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
    @Size(min = 1, max = 30)
    @Column(name = "staff_code")
    private String staffCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "check_in_day")
    @Temporal(TemporalType.DATE)
    private Date checkInDay;
    @Basic(optional = false)
    @NotNull
    @Column(name = "check_in_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private boolean type;
    @Size(max = 255)
    @Column(name = "image")
    private String image;

    public CheckInOut() {
    }

    public CheckInOut(Integer id) {
        this.id = id;
    }

    public CheckInOut(Integer id, int staffId, String staffCode, Date checkInDay, Date checkInAt, boolean type) {
        this.id = id;
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.checkInDay = checkInDay;
        this.checkInAt = checkInAt;
        this.type = type;
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

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Date getCheckInDay() {
        return checkInDay;
    }

    public void setCheckInDay(Date checkInDay) {
        this.checkInDay = checkInDay;
    }

    public Date getCheckInAt() {
        return checkInAt;
    }

    public void setCheckInAt(Date checkInAt) {
        this.checkInAt = checkInAt;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        if (!(object instanceof CheckInOut)) {
            return false;
        }
        CheckInOut other = (CheckInOut) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.CheckInOut[ id=" + id + " ]";
    }
    
}
