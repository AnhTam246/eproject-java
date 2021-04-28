/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author nfsre
 */
@Entity
@Table(name = "salary_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalaryDetail.findAll", query = "SELECT s FROM SalaryDetail s"),
    @NamedQuery(name = "SalaryDetail.findById", query = "SELECT s FROM SalaryDetail s WHERE s.id = :id"),
    @NamedQuery(name = "SalaryDetail.findBySalaryId", query = "SELECT s FROM SalaryDetail s WHERE s.salaryId = :salaryId"),
    @NamedQuery(name = "SalaryDetail.findByStaffId", query = "SELECT s FROM SalaryDetail s WHERE s.staffId = :staffId"),
    @NamedQuery(name = "SalaryDetail.findBySalary", query = "SELECT s FROM SalaryDetail s WHERE s.salary = :salary"),
    @NamedQuery(name = "SalaryDetail.findByKpiReward", query = "SELECT s FROM SalaryDetail s WHERE s.kpiReward = :kpiReward"),
    @NamedQuery(name = "SalaryDetail.findByOtherReward", query = "SELECT s FROM SalaryDetail s WHERE s.otherReward = :otherReward"),
    @NamedQuery(name = "SalaryDetail.findByCreateAt", query = "SELECT s FROM SalaryDetail s WHERE s.createAt = :createAt")})
public class SalaryDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "salary_id")
    private Integer salaryId;

    @Basic(optional = false)
    @Null
    @Column(name = "details", columnDefinition = "json")
    private String details;

    @Basic(optional = false)
    @NotNull
    @Column(name = "staff_id")
    private int staffId;

    @OneToOne
    @JoinColumn(name = "staff_id", insertable = false, updatable = false)
    private Staff staff;

    @Basic(optional = false)
    @NotNull
    @Column(name = "salary")
    private double salary;

    @Column(name = "base_salary_contract")
    private double baseSalaryContract = 0;

    @Column(name = "total_day_work")
    private float totalDayWork = 0;

    @Column(name = "total_special_day")
    private float totalSpecialDay = 0;

    @Basic(optional = false)
    @NotNull
    @Column(name = "salary_ot")
    private double salaryOt;

    @Null
    @Column(name = "allowance_details", columnDefinition = "json")
    private String allowanceDetails;

    @Column(name = "total_allowance")
    private double totalAllowance = 0;

    @Null
    @Column(name = "insurance_details", columnDefinition = "json")
    private String insuranceDetails;

    @Column(name = "total_insurance")
    private double totalInsurance = 0;

    @Null
    @Column(name = "tax_details", columnDefinition = "json")
    private String taxDetails;

    @Column(name = "total_tax")
    private double totalTax = 0;

    @Column(name = "income_tax")
    private double incomeTax = 0;

    @Column(name = "taxable_income")
    private double taxableIncome = 0;

    @Column(name = "personal_tax")
    private double personalTax = 0;

    @Column(name = "salary_actually_received")
    private double salaryActuallyReceived = 0;

// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "kpi_reward")
    private Double kpiReward;
    @Column(name = "other_reward")
    private Double otherReward;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @OneToOne
    @JoinColumn(name = "salary_id", insertable = false, updatable = false)
    private Salary salaryDetail;

    public SalaryDetail() {
    }

    public SalaryDetail(Integer id) {
        this.id = id;
    }

    public SalaryDetail(Integer id, Integer salaryId, String details, double salary) {
        this.id = id;
        this.salaryId = salaryId;
        this.details = details;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public List<HashMap<String, Object>> getDetails() {
        Gson gson = new Gson();
        List<HashMap<String, Object>> listDetail = gson.fromJson(details, new TypeToken<List<HashMap<String, Object>>>() {
        }.getType());;
        return listDetail;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Double getKpiReward() {
        return kpiReward;
    }

    public void setKpiReward(Double kpiReward) {
        this.kpiReward = kpiReward;
    }

    public Double getOtherReward() {
        return otherReward;
    }

    public void setOtherReward(Double otherReward) {
        this.otherReward = otherReward;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getSalaryOt() {
        return salaryOt;
    }

    public void setSalaryOt(double salaryOt) {
        this.salaryOt = salaryOt;
    }

    public String getAllowanceDetails() {
        return allowanceDetails;
    }

    public void setAllowanceDetails(String allowanceDetails) {
        this.allowanceDetails = allowanceDetails;
    }

    public String getInsuranceDetails() {
        return insuranceDetails;
    }

    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }

    public double getTotalAllowance() {
        return totalAllowance;
    }

    public void setTotalAllowance(double totalAllowance) {
        this.totalAllowance += totalAllowance;
    }

    public double getTotalInsurance() {
        return totalInsurance;
    }

    public void setTotalInsurance(double totalInsurance) {
        this.totalInsurance += totalInsurance;
    }

    public double getBaseSalaryContract() {
        return baseSalaryContract;
    }

    public void setBaseSalaryContract(double baseSalaryContract) {
        this.baseSalaryContract = baseSalaryContract;
    }

    public String getTaxDetails() {
        return taxDetails;
    }

    public void setTaxDetails(String taxDetails) {
        this.taxDetails = taxDetails;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(double totalTax) {
        this.totalTax += totalTax;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public void setTaxableIncome(double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }

    public double getSalaryActuallyReceived() {
        return salaryActuallyReceived;
    }

    public void setSalaryActuallyReceived(double salaryActuallyReceived) {
        this.salaryActuallyReceived = salaryActuallyReceived;
    }

    public double getPersonalTax() {
        return personalTax;
    }

    public void setPersonalTax(double personalTax) {
        this.personalTax = personalTax;
    }

    public float getTotalDayWork() {
        return totalDayWork;
    }

    public void setTotalDayWork(float day) {
        this.totalDayWork += day;
    }

    public float getTotalSpecialDay() {
        return totalSpecialDay;
    }

    public void setTotalSpecialDay(float day) {
        this.totalSpecialDay += day;
    }

    public Salary getSalaryDetail() {
        return salaryDetail;
    }

    public void setSalaryDetail(Salary salaryDetail) {
        this.salaryDetail = salaryDetail;
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
        if (!(object instanceof SalaryDetail)) {
            return false;
        }
        SalaryDetail other = (SalaryDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.entities.SalaryDetail[ id=" + id + " ]";
    }

}
