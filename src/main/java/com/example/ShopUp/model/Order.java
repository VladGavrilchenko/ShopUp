package com.example.ShopUp.model;

import com.example.ShopUp.User;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map;
@Entity
@Table(name = "ord")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;
    private boolean isProcess;
    private boolean isDone;
    private BigDecimal price= new BigDecimal(0);

    @ManyToMany
    private List<Guitar> guitars = new ArrayList<>();
    @ManyToMany
    private List<GuitarСase> guitarСases = new ArrayList<>();
    @ManyToMany
    private List<InstrumentCable> instrumentCables = new ArrayList<>();
    @ManyToMany
    private List<ComboAmplifier> comboAmplifiers = new ArrayList<>();
    @ManyToMany
    private List<Mediator> mediators = new ArrayList<>();
    @ManyToMany
    private List<GuitarString> guitarStrings = new ArrayList<>();
    @ManyToMany
    private List<GuitarStrap> guitarStraps = new ArrayList<>();
    @ManyToMany
    private List<GuitarPedal> guitarPedals = new ArrayList<>();




    public Order() {
        this.isProcess = true;
    }
    public Order(User user) {
        this.isProcess = true;
        this.user =user;
        this.isDone =false;


    }
    public boolean isProcess() {
        return isProcess;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<Guitar> getGuitars() {
        return guitars;
    }

    public List<ComboAmplifier> getComboAmplifiers() {
        return comboAmplifiers;
    }

    public List<GuitarСase> getGuitarCases() {
        return guitarСases;
    }

    public List<InstrumentCable> getInstrumentCables() {
        return instrumentCables;
    }

    public List<Mediator> getMediators() {
        return mediators;
    }

    public List<GuitarStrap> getGuitarStraps() {
        return guitarStraps;
    }

    public List<GuitarString> getGuitarStrings() {
        return guitarStrings;
    }

    public List<GuitarPedal> getGuitarPedals() {
        return guitarPedals;
    }

    public void setGuitars(List<Guitar> guitars) {
        this.guitars = guitars;
    }

    public void setGuitarStrings(List<GuitarString> guitarStrings) {
        this.guitarStrings = guitarStrings;
    }

    public void setGuitarStraps(List<GuitarStrap> guitarStraps) {
        this.guitarStraps = guitarStraps;
    }

    public void getGuitarCases(List<GuitarСase> guitarСases) {
        this.guitarСases = guitarСases;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setProcess(boolean process) {
        isProcess = process;
    }

    public void addGuitar(Guitar guitar){
        this.price=this.price.add(guitar.getPrice());
         this.guitars.add(guitar);
    }

    public void addGuitarСase(GuitarСase guitarСase){
        this.price=this.price.add(guitarСase.getPrice());
        this.guitarСases.add(guitarСase);
    }

    public void addInstrumentCable(InstrumentCable instrumentCable){
        this.price=this.price.add(instrumentCable.getPrice());
        this.instrumentCables.add(instrumentCable);
    }

    public void addComboAmplifier(ComboAmplifier comboAmplifier){
        this.price=this.price.add(comboAmplifier.getPrice());
        this.comboAmplifiers.add(comboAmplifier);
    }

    public void addMediator(Mediator mediator){
        this.price= this.price.add(mediator.getPrice());
        this.mediators.add(mediator);
    }

    public void addGuitarString(GuitarString guitarString){
        this.price= this.price.add(guitarString.getPrice());
        this.guitarStrings.add(guitarString);
    }

    public void addGuitarStraps(GuitarStrap guitarStrap){
        this.price = this.price.add(guitarStrap.getPrice());
        this.guitarStraps.add(guitarStrap);
    }

    public void addGuitarPedal(GuitarPedal guitarPedal){
        this.price = this.price.add(guitarPedal.getPrice());
        this.guitarPedals.add(guitarPedal);
    }


    public void subtractOfOrder( BigDecimal price){
        this.price =this.price.subtract(price);

    }



    public void setDone(boolean done) {
        this.isDone = done;
    }


    public  int getIndxGuitar(Guitar guitar){
        return guitars.indexOf(guitar);
    }

    public  int getIndxGuitarString(GuitarString guitarString){
        return guitarStrings.indexOf(guitarString);
    }

    public  int getIndxMediator(Mediator mediator){
        return mediators.indexOf(mediator);
    }

    public  int getIndxInstrumentCable(InstrumentCable instrumentCable){
        return instrumentCables.indexOf(instrumentCable);
    }

    public  int getIndxGuitarStrap(GuitarStrap guitarStrap){
        return guitarStraps.indexOf(guitarStrap);
    }


    public  int getIndxComboAmplifier(ComboAmplifier comboAmplifier){
        return comboAmplifiers.indexOf(comboAmplifier);
    }

    public  int getIndxGuitarCase(GuitarСase guitarСase){
        return guitarСases.indexOf(guitarСase);
    }

    public  int getIndxGuitarPedal(GuitarPedal guitarPedal){
        return guitarPedals.indexOf(guitarPedal);
    }
}
