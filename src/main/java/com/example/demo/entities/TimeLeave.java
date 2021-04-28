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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "time_leave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeLeave.findAll", query = "SELECT t FROM TimeLeave t"),
    @NamedQuery(name = "TimeLeave.findById", query = "SELECT t FROM TimeLeave t WHERE t.id = :id"),
    @NamedQuery(name = "TimeLeave.findByStaffId", query = "SELECT t FROM TimeLeave t WHERE t.staffId = :staffId"),
    @NamedQuery(name = "TimeLeave.findByStaffCode", query = "SELECT t FROM TimeLeave t WHERE t.staffCode = :staffCode"),
    @NamedQuery(name = "TimeLeave.findByDayTimeLeave", query = "SELECT t FROM TimeLeave t WHERE t.dayTimeLeave = :dayTimeLeave"),
    @NamedQuery(name = "TimeLeave.findByTime", query = "SELECT t FROM TimeLeave t WHERE t.time = :time"),
    @NamedQuery(name = "TimeLeave.findByImage", query = "SELECT t FROM TimeLeave t WHERE t.image = :image"),
    @NamedQuery(name = "TimeLeave.findByType", query = "SELECT t FROM TimeLeave t WHERE t.type = :type"),
    @NamedQuery(name = "TimeLeave.findByIsApproved", query = "SELECT t FROM TimeLeave t WHERE t.isApproved = :isApproved"),
    @NamedQuery(name = "TimeLeave.findByDayApproved", query = "SELECT t FROM TimeLeave t WHERE t.dayApproved = :dayApproved"),
    @NamedQuery(name = "TimeLeave.findByCreatedAt", query = "SELECT t FROM TimeLeave t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "TimeLeave.findByDone", query = "SELECT t FROM TimeLeave t WHERE t.done = :done")})
public class TimeLeave implements Serializable {

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
    @Column(name = "day_time_leave")
    @Temporal(TemporalType.DATE)
    private Date dayTimeLeave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Column(name = "type")
    private boolean type;
    @Lob
    @Size(max = 65535)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_approved")
    private int isApproved;
    @Column(name = "day_approved")
    @Temporal(TemporalType.DATE)
    private Date dayApproved;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "done")
    private Integer done;

    public TimeLeave() {
    }

    public TimeLeave(Integer id) {
        this.id = id;
    }

    public TimeLeave(Integer id, int staffId, String staffCode, Date dayTimeLeave, Date time, boolean type, int isApproved) {
        this.id = id;
        this.staffId = staffId;
        this.staffCode = staffCode;
        this.dayTimeLeave = dayTimeLeave;
        this.time = time;
        this.type = type;
        this.isApproved = isApproved;
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

    public Date getDayTimeLeave() {
        return dayTimeLeave;
    }

    public void setDayTimeLeave(Date dayTimeLeave) {
        this.dayTimeLeave = dayTimeLeave;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(int isApproved) {
        this.isApproved = isApproved;
    }

    public Date getDayApproved() {
        return dayApproved;
    }

    public void setDayApproved(Date dayApproved) {
        this.dayApproved = dayApproved;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
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
        if (!(object instanceof TimeLeave)) {
            return false;
        }
        TimeLeave other = (TimeLeave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.TimeLeave[ id=" + id + " ]";
    }
    
}
