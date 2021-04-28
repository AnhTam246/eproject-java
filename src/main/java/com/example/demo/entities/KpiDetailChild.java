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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author nfsre
 */
@Entity
@Table(name = "kpi_detail_child")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KpiDetailChild.findAll", query = "SELECT k FROM KpiDetailChild k"),
    @NamedQuery(name = "KpiDetailChild.findById", query = "SELECT k FROM KpiDetailChild k WHERE k.id = :id"),
    @NamedQuery(name = "KpiDetailChild.findByKpiDetailId", query = "SELECT k FROM KpiDetailChild k WHERE k.kpiDetailId = :kpiDetailId"),
    @NamedQuery(name = "KpiDetailChild.findByNumberTarget", query = "SELECT k FROM KpiDetailChild k WHERE k.numberTarget = :numberTarget"),
    @NamedQuery(name = "KpiDetailChild.findByNumberGet", query = "SELECT k FROM KpiDetailChild k WHERE k.numberGet = :numberGet"),
    @NamedQuery(name = "KpiDetailChild.findByConfidenceLevel", query = "SELECT k FROM KpiDetailChild k WHERE k.confidenceLevel = :confidenceLevel")})
public class KpiDetailChild implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "kpi_detail_id")
    private Integer kpiDetailId;
    @Lob
    @Size(max = 65535)
    @Column(name = "name")
    private String name;
    @Column(name = "number_target")
    private Integer numberTarget;
    @Column(name = "number_get")
    private Integer numberGet;
    @Column(name = "confidence_level")
    private Integer confidenceLevel;
    @Lob
    @Size(max = 65535)
    @Column(name = "duties_activities")
    private String dutiesActivities;
    @Lob
    @Size(max = 65535)
    @Column(name = "skill")
    private String skill;
    @Lob
    @Size(max = 65535)
    @Column(name = "evaluation")
    private String evaluation;

    public KpiDetailChild() {
    }

    public KpiDetailChild(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKpiDetailId() {
        return kpiDetailId;
    }

    public void setKpiDetailId(Integer kpiDetailId) {
        this.kpiDetailId = kpiDetailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumberTarget() {
        return numberTarget;
    }

    public void setNumberTarget(Integer numberTarget) {
        this.numberTarget = numberTarget;
    }

    public Integer getNumberGet() {
        return numberGet;
    }

    public void setNumberGet(Integer numberGet) {
        this.numberGet = numberGet;
    }

    public Integer getConfidenceLevel() {
        return confidenceLevel;
    }

    public void setConfidenceLevel(Integer confidenceLevel) {
        this.confidenceLevel = confidenceLevel;
    }

    public String getDutiesActivities() {
        return dutiesActivities;
    }

    public void setDutiesActivities(String dutiesActivities) {
        this.dutiesActivities = dutiesActivities;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
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
        if (!(object instanceof KpiDetailChild)) {
            return false;
        }
        KpiDetailChild other = (KpiDetailChild) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.KpiDetailChild[ id=" + id + " ]";
    }
    
}
