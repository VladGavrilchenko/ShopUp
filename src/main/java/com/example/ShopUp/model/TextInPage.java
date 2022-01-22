package com.example.ShopUp.model;


import javax.persistence.*;


@Entity
@Table( name = "text_Page")
public class TextInPage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameText;
    @Column(length = 10000)
    private  String text;

    public TextInPage(String text ,String nameText){
          this.text =text;
          this.nameText =nameText;
    }

    public TextInPage(){

    }


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
