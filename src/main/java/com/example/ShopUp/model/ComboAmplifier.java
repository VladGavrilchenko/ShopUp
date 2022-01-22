package com.example.ShopUp.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table( name = "com_amp")
public class ComboAmplifier extends Product {

    private int power;
    private String forGuitar;
    private String regulators;
    private String inputs;

    public  ComboAmplifier(String nameOfProduct, BigDecimal price , int quantity , String descriptions ,
                           String manufacturer, int power , String forGuitar , String regulators, String inputs){
        super(nameOfProduct , price, quantity, descriptions, manufacturer );
        this.power =power;
        this.forGuitar =forGuitar;
        this.regulators=regulators;
        this.inputs=inputs;


    }

    public ComboAmplifier() {

    }

    public int getPower() {
        return power;
    }

    public String getForGuitar() {
        return forGuitar;
    }

    public String getInputs() {
        return inputs;
    }

    public String getRegulators() {
        return regulators;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setForGuitar(String forGuitar) {
        this.forGuitar = forGuitar;
    }

    public void setInputs(String inputs) {
        this.inputs = inputs;
    }

    public void setRegulators(String regulators) {
        this.regulators = regulators;
    }

}
