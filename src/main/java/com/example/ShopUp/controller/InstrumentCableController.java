package com.example.ShopUp.controller;

import com.example.ShopUp.model.*;
import com.example.ShopUp.service.ImageService;
import com.example.ShopUp.service.InstrumentCableService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class InstrumentCableController {

    private final InstrumentCableService instrumentCableService;
    private final ImageService imageService;


    public  InstrumentCableController(InstrumentCableService instrumentCableService ,ImageService imageService ){
        this.instrumentCableService =instrumentCableService;
        this.imageService =imageService;
    }


    @GetMapping("/adminPage/addInstrumentCable")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addInstrumentCable()
    {
        return "addInstrumentCable";
    }

    @PostMapping("/adminPage/addInstrumentCable")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addInstrumentCablePost( @RequestParam  String nameOfProduct, @RequestParam  BigDecimal price ,
                                      @RequestParam  int quantity , @RequestParam  String descriptions ,
                                      @RequestParam  String manufacturer ,  @RequestParam  String connector ,
                                      @RequestParam String color , @RequestParam  double length,
                                     @RequestParam String linkToImage) {

        InstrumentCable instrumentCable = new InstrumentCable(nameOfProduct,price, quantity , descriptions ,
                manufacturer, connector, color , length);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        instrumentCable.getImageOfProducts().add(0,imageOfProduct);

        instrumentCableService.saveInstrumentCable(instrumentCable);


        return "redirect:/adminPage/InstrumentCable";
    }

    @GetMapping("/aboutInstrumentCable{id}")
    public String instrumentCableId(@PathVariable(value = "id") long id , Model model) {
        InstrumentCable instrumentCable = instrumentCableService.findById(id);

        model.addAttribute("instrumentCable",instrumentCable);
        return "aboutInstrumentCable";
    }

    @GetMapping("/InstrumentCable")
    public String instrumentCable(Model model) {
        List<InstrumentCable> instrumentCables = instrumentCableService.findAllShowAndInQuality();
        List<InstrumentCable> instrumentCablesNoQuality = instrumentCableService.findAllShowAndInNoQuality();
        model.addAttribute("instrumentCables" , instrumentCables);
        model.addAttribute("instrumentCablesNoQuality" , instrumentCablesNoQuality);
        return "InstrumentCable";
    }


    @GetMapping("/adminPage/InstrumentCable")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String instrumentCableAdmin(Model model) {


        Iterable<InstrumentCable> instrumentCables = instrumentCableService.findAll();

        model.addAttribute("instrumentCables" , instrumentCables);
        return "InstrumentCableAdmin";
    }


    @GetMapping("/adminPage/editInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editInstrumentCable(@PathVariable(value = "id") long id ,
                               Model model) {
        InstrumentCable instrumentCable = instrumentCableService.findById(id);
        model.addAttribute("instrumentCable" ,instrumentCable );
        return "editInstrumentCable";
    }



    @PostMapping("/adminPage/editInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editInstrumentCablePost(@PathVariable(value = "id") long id,
                                   @RequestParam  String nameOfProduct, @RequestParam  BigDecimal price ,
                                   @RequestParam  int quantity , @RequestParam  String descriptions ,
                                   @RequestParam  String manufacturer ,  @RequestParam  String connector ,
                                   @RequestParam String color , @RequestParam  double length,
                                   @RequestParam String linkToImage) {
        InstrumentCable instrumentCable = instrumentCableService.findById(id);



        instrumentCable.setNameOfProduct(nameOfProduct);
        instrumentCable.setPrice(price);
        instrumentCable.setQuantity(quantity);
        instrumentCable.setDescriptions(descriptions);
        instrumentCable.setManufacturer(manufacturer);
        instrumentCable.setConnector(connector);
        instrumentCable.setColor(color);
        instrumentCable.setLength(length);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        instrumentCable.setFirstImage(linkToImage);



        instrumentCableService.saveInstrumentCable(instrumentCable);


        return "redirect:/adminPage/InstrumentCable";
    }
    @GetMapping("/adminPage/showInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        InstrumentCable instrumentCable = instrumentCableService.findById(id);
        instrumentCable.setShow(true);
        instrumentCableService.saveInstrumentCable(instrumentCable);
        return "redirect:/adminPage/InstrumentCable";
    }


    @GetMapping("/adminPage/dontShowInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        InstrumentCable instrumentCable = instrumentCableService.findById(id);
        instrumentCable.setShow(false);
        instrumentCableService.saveInstrumentCable(instrumentCable);
        return "redirect:/adminPage/InstrumentCable";
    }



    @GetMapping("/adminPage/editQuanityInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost( @PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        InstrumentCable instrumentCable = instrumentCableService.findById(id);
        instrumentCable.editQuantity(quantityEdit);
        instrumentCableService.saveInstrumentCable(instrumentCable);


        return "redirect:/adminPage/InstrumentCable";
    }
}
