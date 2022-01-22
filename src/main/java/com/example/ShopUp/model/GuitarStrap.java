package com.example.ShopUp.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "guit_str")
public class GuitarStrap extends Product{

    private String color;
    private String material;
    private double width;
    private double minLength;
    private double maxLength;

    public  GuitarStrap(String nameOfProduct, BigDecimal price , int quantity , String descriptions ,
                        String manufacturer, String color , String material , double width ,double minLength ,
                        double maxLength){
        super(nameOfProduct,price,quantity,descriptions,manufacturer);
        this.color =color;
        this.material =material;
        this.width =width;
        this.minLength = minLength;
        this.maxLength= maxLength;

    }

    public GuitarStrap() {

    }


    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public double getMaxLength() {
        return maxLength;
    }

    public double getMinLength() {
        return minLength;
    }

    public double getWidth() {
        return width;
    }


    public void setMaterial(String material) {
        this.material = material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    public void setMinLength(double minLength) {
        this.minLength = minLength;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}

