/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entities;

/**
 *
 * @author tonamson
 */
public class ContractDetail {

    String name;
    boolean is_tax;
    Double price;

    public ContractDetail(String name, boolean is_tax, Double price) {
        this.name = name;
        this.is_tax = is_tax;
        this.price = price;
    }

    public boolean isIs_tax() {
        return is_tax;
    }

    public void setIs_tax(boolean is_tax) {
        this.is_tax = is_tax;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
