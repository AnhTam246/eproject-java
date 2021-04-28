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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.*;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "staff")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Staff.findAll", query = "SELECT s FROM Staff s"),
    @NamedQuery(name = "Staff.findById", query = "SELECT s FROM Staff s WHERE s.id = :id"),
    @NamedQuery(name = "Staff.findByCode", query = "SELECT s FROM Staff s WHERE s.code = :code"),
    @NamedQuery(name = "Staff.findByFirstname", query = "SELECT s FROM Staff s WHERE s.firstname = :firstname"),
    @NamedQuery(name = "Staff.findByLastname", query = "SELECT s FROM Staff s WHERE s.lastname = :lastname"),
    @NamedQuery(name = "Staff.findByDepartment", query = "SELECT s FROM Staff s WHERE s.department = :department"),
    @NamedQuery(name = "Staff.findByIsManager", query = "SELECT s FROM Staff s WHERE s.isManager = :isManager"),
    @NamedQuery(name = "Staff.findByJoinedAt", query = "SELECT s FROM Staff s WHERE s.joinedAt = :joinedAt"),
    @NamedQuery(name = "Staff.findByOffDate", query = "SELECT s FROM Staff s WHERE s.offDate = :offDate"),
    @NamedQuery(name = "Staff.findByDob", query = "SELECT s FROM Staff s WHERE s.dob = :dob"),
    @NamedQuery(name = "Staff.findByGender", query = "SELECT s FROM Staff s WHERE s.gender = :gender"),
    @NamedQuery(name = "Staff.findByRegional", query = "SELECT s FROM Staff s WHERE s.regional = :regional"),
    @NamedQuery(name = "Staff.findByPhoneNumber", query = "SELECT s FROM Staff s WHERE s.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "Staff.findByEmail", query = "SELECT s FROM Staff s WHERE s.email = :email"),
    @NamedQuery(name = "Staff.findByPassword", query = "SELECT s FROM Staff s WHERE s.password = :password"),
    @NamedQuery(name = "Staff.findByIdNumber", query = "SELECT s FROM Staff s WHERE s.idNumber = :idNumber"),
    @NamedQuery(name = "Staff.findByPhoto", query = "SELECT s FROM Staff s WHERE s.photo = :photo"),
    @NamedQuery(name = "Staff.findByIdPhoto", query = "SELECT s FROM Staff s WHERE s.idPhoto = :idPhoto"),
    @NamedQuery(name = "Staff.findByIdPhotoBack", query = "SELECT s FROM Staff s WHERE s.idPhotoBack = :idPhotoBack"),
    @NamedQuery(name = "Staff.findByDayOfLeave", query = "SELECT s FROM Staff s WHERE s.dayOfLeave = :dayOfLeave"),
    @NamedQuery(name = "Staff.findByNote", query = "SELECT s FROM Staff s WHERE s.note = :note"),
    @NamedQuery(name = "Staff.findByCreatedAt", query = "SELECT s FROM Staff s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "Staff.findByCreatedBy", query = "SELECT s FROM Staff s WHERE s.createdBy = :createdBy"),
    @NamedQuery(name = "Staff.findByUpdatedAt", query = "SELECT s FROM Staff s WHERE s.updatedAt = :updatedAt"),
    @NamedQuery(name = "Staff.findByUpdatedBy", query = "SELECT s FROM Staff s WHERE s.updatedBy = :updatedBy"),
    @NamedQuery(name = "Staff.findByStatus", query = "SELECT s FROM Staff s WHERE s.status = :status")})
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "code")
    private String code;
    @Size(max = 255)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 255)
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "department")
    private Integer department;
    @Column(name = "is_manager")
    private Boolean isManager;
    @Column(name = "joined_at")
    @Temporal(TemporalType.DATE)
    private Date joinedAt;
    @Column(name = "off_date")
    @Temporal(TemporalType.DATE)
    private Date offDate;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "gender")
    private Short gender;
    @Column(name = "regional")
    private Integer regional;
    @Size(max = 50)
    @Column(name = "phone_number")
    private String phoneNumber;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "password")
    private String password;
    @Size(max = 15)
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "identityIssueDate")
    @Temporal(TemporalType.DATE)
    private Date identity_issue_date;
    @Size(max = 255)
    @Column(name = "photo")
    private String photo;
    @Size(max = 255)
    @Column(name = "id_photo")
    private String idPhoto;
    @Size(max = 255)
    @Column(name = "id_photo_back")
    private String idPhotoBack;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "day_of_leave")
    private Float dayOfLeave;
    @Size(max = 1000)
    @Column(name = "note")
    private String note;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedAt;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "status")
    private boolean status;

    public Staff() {
    }

    public Staff(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Date getOffDate() {
        return offDate;
    }

    public void setOffDate(Date offDate) {
        this.offDate = offDate;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public Integer getRegional() {
        return regional;
    }

    public void setRegional(Integer regional) {
        this.regional = regional;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setIdentity_issue_date(Date identity_issue_date) {
        this.identity_issue_date = identity_issue_date;
    }

    public Date getIdentity_issue_date() {
        return identity_issue_date;
    }
    

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getIdPhotoBack() {
        return idPhotoBack;
    }

    public void setIdPhotoBack(String idPhotoBack) {
        this.idPhotoBack = idPhotoBack;
    }

    public Float getDayOfLeave() {
        return dayOfLeave;
    }

    public void setDayOfLeave(Float dayOfLeave) {
        this.dayOfLeave = dayOfLeave;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
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
        if (!(object instanceof Staff)) {
            return false;
        }
        Staff other = (Staff) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.Staff[ id=" + id + " ]";
    }
    
}
