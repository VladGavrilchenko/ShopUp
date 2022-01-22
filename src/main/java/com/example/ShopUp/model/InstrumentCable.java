package com.example.ShopUp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "inst_cab")
public class InstrumentCable extends Product{
    private String connector;
    private String color;
    private double length;

    public InstrumentCable(String nameOfProduct, BigDecimal price , int quantity , String descriptions ,
                           String manufacturer ,String connector , String color , double length){
        super(nameOfProduct,price,quantity,descriptions,manufacturer);
        this.color =color;
        this.connector=connector;
        this.length=length;
    }

    public InstrumentCable(){}

    public String getColor() {
        return color;
    }

    public double getLength() {
        return length;
    }

    public String getConnector() {
        return connector;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public void setLength(double length) {
        this.length = length;
    }
}
