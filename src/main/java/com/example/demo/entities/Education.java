/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "education")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Education.findAll", query = "SELECT e FROM Education e"),
    @NamedQuery(name = "Education.findById", query = "SELECT e FROM Education e WHERE e.id = :id"),
    @NamedQuery(name = "Education.findByStaffId", query = "SELECT e FROM Education e WHERE e.staffId = :staffId"),
    @NamedQuery(name = "Education.findByLevel", query = "SELECT e FROM Education e WHERE e.level = :level"),
    @NamedQuery(name = "Education.findByLevelName", query = "SELECT e FROM Education e WHERE e.levelName = :levelName"),
    @NamedQuery(name = "Education.findBySchool", query = "SELECT e FROM Education e WHERE e.school = :school"),
    @NamedQuery(name = "Education.findByFieldOfStudy", query = "SELECT e FROM Education e WHERE e.fieldOfStudy = :fieldOfStudy"),
    @NamedQuery(name = "Education.findByGraduatedYear", query = "SELECT e FROM Education e WHERE e.graduatedYear = :graduatedYear"),
    @NamedQuery(name = "Education.findByGrade", query = "SELECT e FROM Education e WHERE e.grade = :grade"),
    @NamedQuery(name = "Education.findByModeOfStudy", query = "SELECT e FROM Education e WHERE e.modeOfStudy = :modeOfStudy")})
public class Education implements Serializable {

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
    @Column(name = "level")
    private int level;
    @Size(max = 255)
    @Column(name = "level_name")
    private String levelName;
    @Size(max = 255)
    @Column(name = "school")
    private String school;
    @Size(max = 255)
    @Column(name = "field_of_study")
    private String fieldOfStudy;
    @Column(name = "graduated_year")
    private Integer graduatedYear;
    @Size(max = 128)
    @Column(name = "grade")
    private String grade;
    @Size(max = 255)
    @Column(name = "mode_of_study")
    private String modeOfStudy;

    public Education() {
    }

    public Education(Integer id) {
        this.id = id;
    }

    public Education(Integer id, int staffId, int level) {
        this.id = id;
        this.staffId = staffId;
        this.level = level;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public Integer getGraduatedYear() {
        return graduatedYear;
    }

    public void setGraduatedYear(Integer graduatedYear) {
        this.graduatedYear = graduatedYear;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getModeOfStudy() {
        return modeOfStudy;
    }

    public void setModeOfStudy(String modeOfStudy) {
        this.modeOfStudy = modeOfStudy;
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
        if (!(object instanceof Education)) {
            return false;
        }
        Education other = (Education) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.Education[ id=" + id + " ]";
    }
    
}
