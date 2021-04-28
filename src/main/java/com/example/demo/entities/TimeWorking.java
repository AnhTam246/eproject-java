/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tonamson
 */
public class TimeWorking {

    private int staff_id;
    private String staff_name;
    private float total_time;
    private double total_salary;
    private double total_salary_ot;
    private List<HashMap<String, Object>> details;

    public TimeWorking() {
    }

    public List<HashMap<String, Object>> getDetails() {
        return details;
    }

    public void setDetails(List<HashMap<String, Object>> details) {
        this.details = details;
    }

    public double getTotal_salary() {
        return total_salary;
    }

    public void setTotal_salary(double total_salary) {
        this.total_salary = total_salary;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public float getTotal_time() {
        return total_time;
    }

    public void setTotal_time(float total_time) {
        this.total_time = total_time;
    }

    public double getTotal_salary_ot() {
        return total_salary_ot;
    }

    public void setTotal_salary_ot(double total_salary_ot) {
        this.total_salary_ot = total_salary_ot;
    }

}
