package com.example.ShopUp.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "guit_string")
public class GuitarString extends  Product{
    private String material;
    private String caliber;

    public GuitarString(String nameOfProduct, BigDecimal price , int quantity , String descriptions ,
                        String manufacturer, String material ,String caliber){
        super(nameOfProduct , price , quantity , descriptions , manufacturer);
        this.material =material;
        this.caliber = caliber;
    }

    public  GuitarString()
    {

    }

    public String getCaliber() {
        return caliber;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setCaliber(String caliber) {
        this.caliber = caliber;
    }
}
