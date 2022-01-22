package com.example.ShopUp.controller;

import com.example.ShopUp.model.*;
import com.example.ShopUp.service.GuitarStrapService;
import com.example.ShopUp.service.ImageService;
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
public class GuitarStrapController {
    private final GuitarStrapService guitarStrapService;
    private final ImageService imageService;

    GuitarStrapController(GuitarStrapService guitarStrapService,ImageService imageService){
        this.guitarStrapService =guitarStrapService;
        this.imageService =imageService;
    }


    @GetMapping("/adminPage/addGuitarStrap")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarStrap()
    {
        return "addGuitarStrap";
    }

    @PostMapping("/adminPage/addGuitarStrap")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarStrapPost(@RequestParam String nameOfProduct, @RequestParam BigDecimal price ,
                                     @RequestParam int quantity ,@RequestParam String descriptions ,
                                     @RequestParam String manufacturer,@RequestParam String color ,
                                     @RequestParam String material ,@RequestParam double width ,
                                     @RequestParam double minLength , @RequestParam double maxLength,
                                     @RequestParam String linkToImage) {

        GuitarStrap guitarStrap = new GuitarStrap(nameOfProduct ,price ,quantity ,descriptions , manufacturer ,color,
                material ,width ,minLength ,maxLength);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarStrap.getImageOfProducts().add(0,imageOfProduct);
        guitarStrapService.saveGuitarStrap(guitarStrap);


        return "redirect:/adminPage/guitarStrap";
    }

    @GetMapping("/aboutGuitarStrap{id}")
    public String guitarStrapId(@PathVariable(value = "id") long id , Model model) {
        GuitarStrap guitarStrap = guitarStrapService.findById(id);

        model.addAttribute("guitarStrap",guitarStrap);
        return "aboutGuitarStrap";
    }

    @GetMapping("/GuitarStrap")
    public String guitarStrap(Model model) {
        List<GuitarStrap> guitarStraps = guitarStrapService.findAllShowAndInQuality();
        List<GuitarStrap> guitarStrapsNoQuality = guitarStrapService.findAllShowAndInNoQuality();
        model.addAttribute("guitarStraps" , guitarStraps);
        model.addAttribute("guitarStrapsNoQuality" , guitarStrapsNoQuality);
        return "GuitarStrap";
    }

    @GetMapping("/adminPage/guitarStrap")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String guitarStrapAdmin(Model model)
    {


        Iterable<GuitarStrap> guitarStraps = guitarStrapService.findAll();

        model.addAttribute("guitarStraps" , guitarStraps);
        return "GuitarStrapAdmin";
    }

    @GetMapping("/adminPage/editQuanityGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost(@PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        GuitarStrap guitarStrap = guitarStrapService.findById(id);
        guitarStrap.editQuantity(quantityEdit);
        guitarStrapService.saveGuitarStrap(guitarStrap);


        return "redirect:/adminPage/guitarStrap";
    }

    @GetMapping("/adminPage/showGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        GuitarStrap guitarStrap = guitarStrapService.findById(id);
        guitarStrap.setShow(true);
        guitarStrapService.saveGuitarStrap(guitarStrap);
        return "redirect:/adminPage/guitarStrap";
    }


    @GetMapping("/adminPage/dontShowGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        GuitarStrap guitarStrap = guitarStrapService.findById(id);
        guitarStrap.setShow(false);
        guitarStrapService.saveGuitarStrap(guitarStrap);
        return "redirect:/adminPage/guitarStrap";
    }


    @GetMapping("/adminPage/editGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarStrap(@PathVariable(value = "id") long id ,
                               Model model) {
        GuitarStrap guitarStrap= guitarStrapService.findById(id);
        model.addAttribute("guitarStrap" ,guitarStrap );
        return "editGuitarStrap";
    }



    @PostMapping("/adminPage/editGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarStrapPost(@PathVariable(value = "id") long id,
                                   @RequestParam String nameOfProduct, @RequestParam BigDecimal price ,
                                   @RequestParam int quantity ,@RequestParam String descriptions ,
                                   @RequestParam String manufacturer,@RequestParam String color ,
                                   @RequestParam String material ,@RequestParam double width ,
                                   @RequestParam double minLength , @RequestParam double maxLength,
                                   @RequestParam String linkToImage) {
        GuitarStrap guitarStrap= guitarStrapService.findById(id);



        guitarStrap.setNameOfProduct(nameOfProduct);
        guitarStrap.setPrice(price);
        guitarStrap.setQuantity(quantity);
        guitarStrap.setDescriptions(descriptions);
        guitarStrap.setManufacturer(manufacturer);
        guitarStrap.setColor(color);
        guitarStrap.setMaterial(material);
        guitarStrap.setWidth(width);
        guitarStrap.setMinLength(minLength);
        guitarStrap.setMaxLength(maxLength);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarStrap.setFirstImage(linkToImage);



        guitarStrapService.saveGuitarStrap(guitarStrap);


        return "redirect:/adminPage/guitarStrap";
    }


}
