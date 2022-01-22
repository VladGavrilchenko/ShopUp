package com.example.ShopUp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "guit_cas")
public class GuitarСase extends Product{
    private String forGuitar;
    private String color;
    private int  insulation;

    public GuitarСase(String nameOfProduct, BigDecimal price , int quantity , String descriptions ,
                      String manufacturer,String forGuitar , String color , int insulation) {
        super(nameOfProduct,price,quantity,descriptions,manufacturer);
        this.forGuitar=forGuitar;
        this.color=color;
        this.insulation=insulation;

    }


    public String getColor() {
        return color;
    }

    public String getForGuitar() {
        return forGuitar;
    }

    public int getInsulation() {
        return insulation;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setForGuitar(String forGuitar) {
        this.forGuitar = forGuitar;
    }

    public void setInsulation(int insulation) {
        this.insulation = insulation;
    }

    public GuitarСase()
    {

    }
}
