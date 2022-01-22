package com.example.ShopUp.controller;

import com.example.ShopUp.model.*;
import com.example.ShopUp.service.GuitarСaseService;
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
public class GuitarСaseController {

    private final GuitarСaseService guitarСaseService;
    private final ImageService imageService;

    GuitarСaseController(GuitarСaseService guitarСaseService,ImageService imageService){
        this.guitarСaseService =guitarСaseService;
        this.imageService =imageService;
    }

    @GetMapping("/adminPage/addGuitarCase")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarCase()
    {
        return "addGuitarСase";
    }

    @PostMapping("/adminPage/addGuitarCase")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarCasePost( @RequestParam String nameOfProduct, @RequestParam BigDecimal price ,
                                      @RequestParam int quantity , @RequestParam String descriptions ,
                                      @RequestParam String manufacturer,  @RequestParam String forGuitar ,
                                      @RequestParam String color , @RequestParam int insulation,
                                     @RequestParam String linkToImage) {

        GuitarСase guitarСase = new GuitarСase(nameOfProduct,price, quantity , descriptions , manufacturer,
                forGuitar, color , insulation );
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarСase.getImageOfProducts().add(0,imageOfProduct);

        guitarСaseService.saveGuitarСase(guitarСase);


        return "redirect:/adminPage/guitarCases";
    }

    @GetMapping("/aboutGuitarCase{id}")
    public String aboutGuitarCaseid(@PathVariable(value = "id") long id , Model model) {
        GuitarСase guitarCase = guitarСaseService.findById(id);

        model.addAttribute("guitarCase",guitarCase);
        return "aboutGuitarCase";
    }

    @GetMapping("/GuitarCase")
    public String guitarCase(Model model) {
        List<GuitarСase> guitarСases = guitarСaseService.findAllShowAndInQuality();
        List<GuitarСase> guitarСasesNoQuality = guitarСaseService.findAllShowAndInNoQuality();
        model.addAttribute("guitarCases" , guitarСases);
        model.addAttribute("guitarCasesNoQuality" , guitarСasesNoQuality);
        return "GuitarCase";
    }


    @GetMapping("/adminPage/guitarCases")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String guitarCasesAdmin(Model model)
    {


        Iterable<GuitarСase> guitarCases = guitarСaseService.findAll();

        model.addAttribute("guitarCases" , guitarCases);
        return "GuitarСaseAdmin";
    }


    @GetMapping("/adminPage/showGuitarСase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        GuitarСase guitarСase = guitarСaseService.findById(id);
        guitarСase.setShow(true);
        guitarСaseService.saveGuitarСase(guitarСase);
        return "redirect:/adminPage/guitarCases";
    }


    @GetMapping("/adminPage/dontShowGuitarСase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        GuitarСase guitarСase = guitarСaseService.findById(id);
        guitarСase.setShow(false);
        guitarСaseService.saveGuitarСase(guitarСase);
        return "redirect:/adminPage/guitarCases";
    }

    @GetMapping("/adminPage/editQuanityGuitarСase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityGuitarСase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost( @PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        GuitarСase guitarСase = guitarСaseService.findById(id);
        guitarСase.editQuantity(quantityEdit);
        guitarСaseService.saveGuitarСase(guitarСase);


        return "redirect:/adminPage/guitarCases";
    }


    @GetMapping("/adminPage/editGuitarСase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarСase(@PathVariable(value = "id") long id ,
                               Model model) {
        GuitarСase guitarСase = guitarСaseService.findById(id);
        model.addAttribute("guitarСase" ,guitarСase );
        return "editGuitarСase";
    }



    @PostMapping("/adminPage/editGuitarСase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarСasePost(@PathVariable(value = "id") long id,
                                   @RequestParam String nameOfProduct, @RequestParam BigDecimal price ,
                                   @RequestParam int quantity , @RequestParam String descriptions ,
                                   @RequestParam String manufacturer,  @RequestParam String forGuitar ,
                                   @RequestParam String color , @RequestParam int insulation,
                                   @RequestParam String linkToImage) {
        GuitarСase guitarСase = guitarСaseService.findById(id);



        guitarСase.setNameOfProduct(nameOfProduct);
        guitarСase.setPrice(price);
        guitarСase.setQuantity(quantity);
        guitarСase.setDescriptions(descriptions);
        guitarСase.setManufacturer(manufacturer);
        guitarСase.setForGuitar(forGuitar);
        guitarСase.setColor(color);
        guitarСase.setInsulation(insulation);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarСase.setFirstImage(linkToImage);



        guitarСaseService.saveGuitarСase(guitarСase);


        return "redirect:/adminPage/guitarCases";
    }
}
