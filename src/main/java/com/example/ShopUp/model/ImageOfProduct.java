package com.example.ShopUp.model;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "img")
public class ImageOfProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String link;

    public ImageOfProduct(String link)
    {
        this.link =link;
    }

    public ImageOfProduct()
    {


    }

    public String getLink() {
        return link;
    }

    public Long getId() {
        return id;
    }

    public void setLink(String link) {

        if (link.startsWith("https://drive.google.com/file/d/"))
        {
           link = editGoogleDiskLinkForHtml(link);
        }
        this.link = link;
    }



    public  String editGoogleDiskLinkForHtml( String link){

        int startNum = 32;
        int endNum = link.length();
        link = link.substring(startNum, endNum);
        endNum = link.length();
        if(link.endsWith("/view")){

            link = link.substring(0, endNum -5);
        }

        else {

            link = link.substring(0, endNum -17);
        }
        link = "http://drive.google.com/uc?export=view&id=" + link;
        return  link;
    }
}
