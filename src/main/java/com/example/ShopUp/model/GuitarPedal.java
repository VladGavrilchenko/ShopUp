package com.example.ShopUp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "guit_ped")
public class GuitarPedal extends Product{
    private String forGuitar;
    private String effect;
    private String deviceType;
    private String format;

    public GuitarPedal(String nameOfProduct, BigDecimal price , int quantity , String descriptions ,
                String manufacturer, String forGuitar , String effect , String deviceType , String format) {
        super(nameOfProduct , price , quantity ,descriptions ,manufacturer);
        this.deviceType =deviceType;
        this.forGuitar= forGuitar;
        this.effect =effect;
        this.format =format;
    }

    public GuitarPedal() {

    }

    public String getForGuitar() {
        return forGuitar;
    }

    public String getFormat() {
        return format;
    }

    public String getEffect() {
        return effect;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setForGuitar(String forGuitar) {
        this.forGuitar = forGuitar;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }
}
