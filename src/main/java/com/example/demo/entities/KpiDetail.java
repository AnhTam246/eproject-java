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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Red
 */
@Entity
@Table(name = "kpi_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KpiDetail.findAll", query = "SELECT k FROM KpiDetail k"),
    @NamedQuery(name = "KpiDetail.findById", query = "SELECT k FROM KpiDetail k WHERE k.id = :id"),
    @NamedQuery(name = "KpiDetail.findByKpiId", query = "SELECT k FROM KpiDetail k WHERE k.kpiId = :kpiId"),
    @NamedQuery(name = "KpiDetail.findByTaskTarget", query = "SELECT k FROM KpiDetail k WHERE k.taskTarget = :taskTarget"),
    @NamedQuery(name = "KpiDetail.findByRatio", query = "SELECT k FROM KpiDetail k WHERE k.ratio = :ratio"),
    @NamedQuery(name = "KpiDetail.findByDel", query = "SELECT k FROM KpiDetail k WHERE k.del = :del")})
public class KpiDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "kpi_id")
    private int kpiId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "task_target")
    private String taskTarget;
    @Lob
    @Size(max = 65535)
    @Column(name = "task_description")
    private String taskDescription;
    @Lob
    @Size(max = 65535)
    @Column(name = "duties_activities")
    private String dutiesActivities;
    @Lob
    @Size(max = 65535)
    @Column(name = "skill")
    private String skill;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratio")
    private int ratio;
    @Column(name = "del")
    private Boolean del;

    public KpiDetail() {
    }

    public KpiDetail(Integer id) {
        this.id = id;
    }

    public KpiDetail(Integer id, int kpiId, String taskTarget, int ratio) {
        this.id = id;
        this.kpiId = kpiId;
        this.taskTarget = taskTarget;
        this.ratio = ratio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getKpiId() {
        return kpiId;
    }

    public void setKpiId(int kpiId) {
        this.kpiId = kpiId;
    }

    public String getTaskTarget() {
        return taskTarget;
    }

    public void setTaskTarget(String taskTarget) {
        this.taskTarget = taskTarget;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
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

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
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
        if (!(object instanceof KpiDetail)) {
            return false;
        }
        KpiDetail other = (KpiDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.KpiDetail[ id=" + id + " ]";
    }
    
}
