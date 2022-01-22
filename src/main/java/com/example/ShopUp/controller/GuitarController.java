package com.example.ShopUp.controller;


import com.example.ShopUp.model.Guitar;
import com.example.ShopUp.model.ImageOfProduct;
import com.example.ShopUp.service.GuitarService;
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
public class GuitarController {

    private final GuitarService guitarService;
    private final ImageService imageService;
    GuitarController(GuitarService guitarService,ImageService imageService){
        this.guitarService =guitarService;
        this.imageService =imageService;
    }


    @GetMapping("/adminPage/addGuitar")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitar()
    {
        return "addGuitar";
    }

    @PostMapping("/adminPage/addGuitar")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarPost(@RequestParam String  nameOfProduct , @RequestParam BigDecimal price,
                                @RequestParam  int quantity ,@RequestParam String descriptions ,
                                @RequestParam String manufacturer , @RequestParam String typeGuitar ,
                                @RequestParam String color  , @RequestParam String material ,@RequestParam String pickup,
                                @RequestParam int countOfFreats ,
                                @RequestParam int scale , @RequestParam String control ,@RequestParam String linkToImage) {

        Guitar guitar = new Guitar(nameOfProduct,price, quantity , descriptions , manufacturer, typeGuitar , color ,
                material, pickup , countOfFreats,scale ,control);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitar.getImageOfProducts().add(0,imageOfProduct);

        guitarService.saveGuitar(guitar);


        return "redirect:/adminPage/guitar";
    }

    @GetMapping("/adminPage/guitar")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String guitarAdmin(Model model)
    {


        Iterable<Guitar> guitars = guitarService.findAll();

        model.addAttribute("guitars" , guitars);
        return "GuitarAdmin";
    }


    @GetMapping("/adminPage/editQuanityGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost( @PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        Guitar guitar = guitarService.findById(id);
        guitar.editQuantity(quantityEdit);
        guitarService.saveGuitar(guitar);


        return "redirect:/adminPage/guitar";
    }

    @GetMapping("/adminPage/editGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitar(@PathVariable(value = "id") long id ,
                               Model model) {
        Guitar guitar = guitarService.findById(id);
        model.addAttribute("guitar" ,guitar );
        return "editGuitar";
    }



    @PostMapping("/adminPage/editGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarPost(@PathVariable(value = "id") long id,
                                   @RequestParam String  nameOfProduct , @RequestParam BigDecimal price,
                                   @RequestParam  int quantity ,@RequestParam String descriptions ,
                                   @RequestParam String manufacturer , @RequestParam String typeGuitar ,
                                   @RequestParam String color  , @RequestParam String material ,
                                   @RequestParam String pickup, @RequestParam int countOfFreats ,
                                   @RequestParam int scale , @RequestParam String control,
                                   @RequestParam String linkToImage) {
        Guitar guitar = guitarService.findById(id);



        guitar.setNameOfProduct(nameOfProduct);
        guitar.setPrice(price);
        guitar.setQuantity(quantity);
        guitar.setDescriptions(descriptions);
        guitar.setManufacturer(manufacturer);
        guitar.setTypeGuitar(typeGuitar);
        guitar.setColor(color);
        guitar.setMaterial(material);
        guitar.setPickup(pickup);
        guitar.setCountOfFreats(countOfFreats);
        guitar.setScale(scale);
        guitar.setControl(control);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitar.setFirstImage(linkToImage);



        guitarService.saveGuitar(guitar);


        return "redirect:/adminPage/guitar";
    }


    @GetMapping("/adminPage/showGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        Guitar guitar = guitarService.findById(id);
        guitar.setShow(true);
        guitarService.saveGuitar(guitar);
        return "redirect:/adminPage/guitar";
    }

    @GetMapping("/guitar")
    public String guitar(Model model) {
        List<Guitar> guitars = guitarService.findAllShowAndInQuality();
        List<Guitar> guitarsNoQuality = guitarService.findAllShowAndInNoQuality();
        model.addAttribute("guitars" , guitars);
        model.addAttribute("guitarsNoQuality" , guitarsNoQuality);
        return "guitar";
    }

    @GetMapping("/aboutGuitar{id}")
    public String guitarId(@PathVariable(value = "id") long id , Model model) {
        Guitar guitar = guitarService.findById(id);

        model.addAttribute("guitar",guitar);
        return "aboutGuitar";
    }



    @GetMapping("/adminPage/dontShowGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        Guitar guitar = guitarService.findById(id);
        guitar.setShow(false);
        guitarService.saveGuitar(guitar);
        return "redirect:/adminPage/guitar";
    }
}
