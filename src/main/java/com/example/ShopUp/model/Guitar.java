package com.example.ShopUp.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table( name = "guit")
public class Guitar extends Product {

    private String typeGuitar;
    private String color;
    private  String material;
    private  String pickup;
    private int countOfFreats;
    private int scale;
    private  String control;

    public Guitar(String nameOfProduct, BigDecimal price , int quantity , String descriptions , String manufacturer,
                  String typeGuitar , String color  ,String material , String pickup ,int countOfFreats ,int scale ,String control ){
        super(nameOfProduct,price,quantity,descriptions,manufacturer);
        this.typeGuitar =typeGuitar;
        this.color =color;
        this.material =material;
        this.pickup =pickup;
        this.countOfFreats =countOfFreats;
        this.scale =scale;
        this.control =control;
    }
    public Guitar(){
        super();
    }

    public void setControl(String control) {
        this.control = control;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public void setTypeGuitar(String typeGuitar) {
        this.typeGuitar = typeGuitar;
    }

    @Override
    public void setShow(boolean show) {
        super.setShow(show);
    }

    @Override
    public void setPrice(BigDecimal price) {
        super.setPrice(price);
    }

    @Override
    public void setDescriptions(String descriptions) {
        super.setDescriptions(descriptions);
    }

    @Override
    public void setFirstImage(String link) {
        super.setFirstImage(link);
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void setImageOfProducts(List<ImageOfProduct> imageOfProducts) {
        super.setImageOfProducts(imageOfProducts);
    }

    @Override
    public void setManufacturer(String manufacturer) {
        super.setManufacturer(manufacturer);
    }

    @Override
    public void setNameOfProduct(String nameOfProduct) {
        super.setNameOfProduct(nameOfProduct);
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String getNameOfProduct() {
        return super.getNameOfProduct();
    }

    @Override
    public String getDescriptions() {
        return super.getDescriptions();
    }

    public void setCountOfFreats(int countOfFreats) {
        this.countOfFreats = countOfFreats;
    }

    @Override
    public String getManufacturer() {
        return super.getManufacturer();
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getMaterial() {
        return material;
    }

    public String getPickup() {
        return pickup;
    }

    public String getControl() {
        return control;
    }

    public String getTypeGuitar() {
        return typeGuitar;
    }

    public String getColor() {
        return color;
    }

    public int getCountOfFreats() {
        return countOfFreats;
    }

    public int getScale() {
        return scale;
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }



}


