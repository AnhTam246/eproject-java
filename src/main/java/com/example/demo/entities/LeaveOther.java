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
@Table(name = "leave_other")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveOther.findAll", query = "SELECT l FROM LeaveOther l"),
    @NamedQuery(name = "LeaveOther.findById", query = "SELECT l FROM LeaveOther l WHERE l.id = :id"),
    @NamedQuery(name = "LeaveOther.findByStaffId", query = "SELECT l FROM LeaveOther l WHERE l.staffId = :staffId"),
    @NamedQuery(name = "LeaveOther.findByTypeLeave", query = "SELECT l FROM LeaveOther l WHERE l.typeLeave = :typeLeave"),
    @NamedQuery(name = "LeaveOther.findByFromDate", query = "SELECT l FROM LeaveOther l WHERE l.fromDate = :fromDate"),
    @NamedQuery(name = "LeaveOther.findByToDate", query = "SELECT l FROM LeaveOther l WHERE l.toDate = :toDate"),
    @NamedQuery(name = "LeaveOther.findByImage", query = "SELECT l FROM LeaveOther l WHERE l.image = :image"),
    @NamedQuery(name = "LeaveOther.findByIsApproved", query = "SELECT l FROM LeaveOther l WHERE l.isApproved = :isApproved"),
    @NamedQuery(name = "LeaveOther.findByDayApproved", query = "SELECT l FROM LeaveOther l WHERE l.dayApproved = :dayApproved"),
    @NamedQuery(name = "LeaveOther.findByCreatedAt", query = "SELECT l FROM LeaveOther l WHERE l.createdAt = :createdAt"),
    @NamedQuery(name = "LeaveOther.findByDone", query = "SELECT l FROM LeaveOther l WHERE l.done = :done")})
public class LeaveOther implements Serializable {

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
    @Column(name = "type_leave")
    private int typeLeave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Lob
    @Size(max = 65535)
    @Column(name = "note")
    private String note;
    @Column(name = "is_approved")
    private Integer isApproved;
    @Column(name = "day_approved")
    @Temporal(TemporalType.DATE)
    private Date dayApproved;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "done")
    private Integer done;

    public LeaveOther() {
    }

    public LeaveOther(Integer id) {
        this.id = id;
    }

    public LeaveOther(Integer id, int staffId, int typeLeave, Date fromDate, Date toDate) {
        this.id = id;
        this.staffId = staffId;
        this.typeLeave = typeLeave;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public int getTypeLeave() {
        return typeLeave;
    }

    public void setTypeLeave(int typeLeave) {
        this.typeLeave = typeLeave;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
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
        if (!(object instanceof LeaveOther)) {
            return false;
        }
        LeaveOther other = (LeaveOther) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.LeaveOther[ id=" + id + " ]";
    }
    
}
