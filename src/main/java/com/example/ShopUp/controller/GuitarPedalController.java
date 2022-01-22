package com.example.ShopUp.controller;


import com.example.ShopUp.model.GuitarPedal;
import com.example.ShopUp.model.ImageOfProduct;
import com.example.ShopUp.service.GuitarPedalService;
import com.example.ShopUp.service.ImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class GuitarPedalController {

    private final GuitarPedalService guitarPedalService;
    private final ImageService imageService;
    GuitarPedalController(GuitarPedalService guitarPedalService,ImageService imageService){
        this.guitarPedalService =guitarPedalService;
        this.imageService =imageService;
    }

    @GetMapping("/adminPage/addGuitarPedal")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarPedal()
    {
        return "addGuitarPedal";
    }

    @PostMapping("/adminPage/addGuitarPedal")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarPedalPost(@RequestParam String  nameOfProduct , @RequestParam BigDecimal price,
                                @RequestParam  int quantity , @RequestParam String descriptions ,
                                @RequestParam String manufacturer ,@RequestParam String forGuitar ,
                                     @RequestParam String effect , @RequestParam String deviceType ,
                                     @RequestParam String format, @RequestParam String linkToImage) {

        GuitarPedal guitarPedal = new GuitarPedal(nameOfProduct,price, quantity , descriptions , manufacturer,
                forGuitar, effect , deviceType, format );
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarPedal.getImageOfProducts().add(0,imageOfProduct);

        guitarPedalService.saveGuitarPedal(guitarPedal);


        return "redirect:/adminPage/guitarPedal";
    }

    @GetMapping("/GuitarPedals")
    public String guitarPedal(Model model) {


        Iterable<GuitarPedal> guitarPedals = guitarPedalService.findAllShowAndInQuality();
        Iterable<GuitarPedal> guitarPedalsNoQuality = guitarPedalService.findAllShowAndInNoQuality();
        model.addAttribute("guitarPedals" , guitarPedals);
        model.addAttribute("guitarPedalsNoQuality" , guitarPedalsNoQuality);
        return "guitarPedal";
    }


    @GetMapping("/aboutGuitarPedal{id}")
    public String guitarPedalId(@PathVariable(value = "id") long id , Model model) {
        GuitarPedal guitarPedal = guitarPedalService.findById(id);

        model.addAttribute("guitarPedal",guitarPedal);
        return "aboutGuitarPedals";
    }



    @GetMapping("/adminPage/guitarPedal")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String guitarPedalAdmin(Model model) {


        Iterable<GuitarPedal> guitarPedals = guitarPedalService.findAll();

        model.addAttribute("guitarPedals" , guitarPedals);
        return "GuitarPedalAdmin";
    }

    @GetMapping("/adminPage/editQuanityGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost( @PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        GuitarPedal guitarPedal = guitarPedalService.findById(id);
        guitarPedal.editQuantity(quantityEdit);
        guitarPedalService.saveGuitarPedal(guitarPedal);


        return "redirect:/adminPage/guitarPedal";
    }



    @GetMapping("/adminPage/editGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarPedal(@PathVariable(value = "id") long id ,
                               Model model) {
        GuitarPedal guitarPedal = guitarPedalService.findById(id);
        model.addAttribute("guitarPedal" ,guitarPedal );
        return "editGuitarPedal";
    }



    @PostMapping("/adminPage/editGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarPedalPost(@PathVariable(value = "id") long id,@RequestParam String  nameOfProduct ,
                                   @RequestParam BigDecimal price,
                                   @RequestParam  int quantity , @RequestParam String descriptions ,
                                   @RequestParam String manufacturer ,@RequestParam String forGuitar ,
                                   @RequestParam String effect , @RequestParam String deviceType ,
                                   @RequestParam String format, @RequestParam String linkToImage) {
        GuitarPedal guitarPedal = guitarPedalService.findById(id);



        guitarPedal.setNameOfProduct(nameOfProduct);
        guitarPedal.setPrice(price);
        guitarPedal.setQuantity(quantity);
        guitarPedal.setDescriptions(descriptions);
        guitarPedal.setManufacturer(manufacturer);
        guitarPedal.setForGuitar(forGuitar);
        guitarPedal.setEffect(effect);
        guitarPedal.setDeviceType(deviceType);
        guitarPedal.setFormat(format);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarPedal.setFirstImage(linkToImage);



        guitarPedalService.saveGuitarPedal(guitarPedal);


        return "redirect:/adminPage/guitarPedal";
    }


    @GetMapping("/adminPage/showGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        GuitarPedal guitarPedal = guitarPedalService.findById(id);
        guitarPedal.setShow(true);
        guitarPedalService.saveGuitarPedal(guitarPedal);
        return "redirect:/adminPage/guitarPedal";
    }


    @GetMapping("/adminPage/dontShowGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        GuitarPedal guitarPedal = guitarPedalService.findById(id);
        guitarPedal.setShow(false);
        guitarPedalService.saveGuitarPedal(guitarPedal);
        return "redirect:/adminPage/guitarPedal";
    }

}
