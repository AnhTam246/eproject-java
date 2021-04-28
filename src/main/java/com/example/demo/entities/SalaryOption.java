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
import javax.persistence.Table;

/**
 *
 * @author tonamson
 */
@Entity
@Table(name = "salary_options")
public class SalaryOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type; // allowance, insurance, tax

    @Column(name = "have_tax")
    private boolean have_tax;

    @Column(name = "key")
    private String key;

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "value")
    private double value;
    
    @Column(name = "min_tax")
    private double min_tax;

    public SalaryOption() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHave_tax() {
        return have_tax;
    }

    public void setHave_tax(boolean have_tax) {
        this.have_tax = have_tax;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getMin_tax() {
        return min_tax;
    }

    public void setMin_tax(double min_tax) {
        this.min_tax = min_tax;
    }

}
