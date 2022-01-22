package com.example.ShopUp.model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "med")
public class Mediator extends Product {

    private String color;
    private double thickness;
    private String material;

     public   Mediator(String nameOfProduct, BigDecimal price , int quantity , String descriptions , String manufacturer,
             String color , double thickness , String material) {
        super(nameOfProduct,price,quantity,descriptions,manufacturer);
        this.color= color;
        this.thickness =thickness;
        this.material =material;

    }

    public  Mediator() {}

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public double getThickness() {
        return thickness;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
}
