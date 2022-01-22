package com.example.ShopUp.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@MappedSuperclass
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String nameOfProduct;
    private BigDecimal price;
    private int quantity;
    @Column(length = 10000)
    private  String descriptions;
    private String manufacturer;
    private boolean isShow;

    @OneToMany
    private List<ImageOfProduct> imageOfProducts = new ArrayList<>();

     public Product(String nameOfProduct,  BigDecimal price ,int quantity ,String descriptions , String manufacturer) {
         this.nameOfProduct = nameOfProduct;
         this.price = price;
         this.quantity =quantity;
         this.descriptions =descriptions;
         this.manufacturer =manufacturer;
         this.isShow = true;

     }
     public  Product(){}


    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public List<ImageOfProduct> getImageOfProducts() {
        return imageOfProducts;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public int getQuantity() {
        return quantity;
    }


    public boolean isShow() {
        return isShow;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setImageOfProducts(List<ImageOfProduct> imageOfProducts) {
        this.imageOfProducts = imageOfProducts;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public  void setFirstImage(String link) {
         this.imageOfProducts.get(0).setLink(link);
     }

     public  void editQuantity(int quantityEdit)
     {
         this.quantity += quantityEdit;

         if(this.quantity <0){
             this.quantity=0;
         }
     }

     public String getFirstImage()
     {
         return   this.imageOfProducts.get(0).getLink();
     }





    public void setShow(boolean show) {
        isShow = show;
    }
}
