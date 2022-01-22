package com.example.ShopUp.controller;

import com.example.ShopUp.model.*;
import com.example.ShopUp.service.ImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import  com.example.ShopUp.service.СomboAmplifierService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.ShopUp.model.ComboAmplifier;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class СomboAmplifierController {

    private final СomboAmplifierService сomboAmplifierService;
    private final ImageService imageService;

    public СomboAmplifierController(СomboAmplifierService сomboAmplifierService, ImageService imageService)
    {
        this.сomboAmplifierService =сomboAmplifierService;
        this.imageService=imageService;
    }

    @GetMapping("/СomboAmplifier")
    public String comboAmplifier(Model model) {
        List<ComboAmplifier> сomboAmplifiers = сomboAmplifierService.findAllShowAndInQuality();
        List<ComboAmplifier> сomboAmplifiersNoQuality = сomboAmplifierService.findAllShowAndInNoQuality();
        model.addAttribute("comboAmplifiers" , сomboAmplifiers);
        model.addAttribute("comboAmplifiersNoQuality" , сomboAmplifiersNoQuality);
        return "СomboAmplifier";
    }

    @GetMapping("/aboutСomboAmplifier{id}")
    public String comboAmplifierId(@PathVariable(value = "id") long id , Model model) {
        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);

        model.addAttribute("comboAmplifier",comboAmplifier);
        return "aboutСomboAmplifier";
    }

    @GetMapping("/adminPage/addСomboAmplifier")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addComboAmplifier()
    {
        return "addСomboAmplifier";
    }

    @PostMapping("/adminPage/addСomboAmplifier")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addComboAmplifierPost( @RequestParam String nameOfProduct,@RequestParam BigDecimal price ,
                                 @RequestParam int quantity ,@RequestParam String descriptions ,
                                 @RequestParam String manufacturer,@RequestParam int power ,
                                 @RequestParam String forGuitar ,@RequestParam String regulators,
                                 @RequestParam String inputs, @RequestParam String linkToImage) {

        ComboAmplifier comboAmplifier = new ComboAmplifier(nameOfProduct,price, quantity , descriptions , manufacturer,
                power , forGuitar , regulators, inputs );
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        comboAmplifier.getImageOfProducts().add(0,imageOfProduct);

        сomboAmplifierService.save(comboAmplifier);


        return "redirect:/adminPage/comboAmplifier";
    }

    @GetMapping("/adminPage/comboAmplifier")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addComboAmplifierAdmin(Model model)
    {


        Iterable<ComboAmplifier> comboamplifier = сomboAmplifierService.getAll();

        model.addAttribute("comboAmplifier" , comboamplifier);
        return "СomboAmplifierAdmin";
    }

    @GetMapping("/adminPage/editQuanityСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost(@PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);
        comboAmplifier.editQuantity(quantityEdit);
        сomboAmplifierService.save(comboAmplifier);


        return "redirect:/adminPage/comboAmplifier";
    }


    @GetMapping("/adminPage/showСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);
        comboAmplifier.setShow(true);
        сomboAmplifierService.save(comboAmplifier);
        return "redirect:/adminPage/comboAmplifier";
    }


    @GetMapping("/adminPage/dontShowСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);
        comboAmplifier.setShow(false);
        сomboAmplifierService.save(comboAmplifier);
        return "redirect:/adminPage/comboAmplifier";
    }


    @GetMapping("/adminPage/editСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editComboAmplifier(@PathVariable(value = "id") long id ,
                               Model model) {
        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);
        model.addAttribute("comboAmplifier" ,comboAmplifier );
        return "editComboAmplifier";
    }



    @PostMapping("/adminPage/editСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editComboAmplifierPost(@PathVariable(value = "id") long id,
                                   @RequestParam String nameOfProduct,@RequestParam BigDecimal price ,
                                   @RequestParam int quantity ,@RequestParam String descriptions ,
                                   @RequestParam String manufacturer,@RequestParam int power ,
                                   @RequestParam String forGuitar ,@RequestParam String regulators,
                                   @RequestParam String inputs, @RequestParam String linkToImage) {
        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);



        comboAmplifier.setNameOfProduct(nameOfProduct);
        comboAmplifier.setPrice(price);
        comboAmplifier.setQuantity(quantity);
        comboAmplifier.setDescriptions(descriptions);
        comboAmplifier.setManufacturer(manufacturer);
        comboAmplifier.setPower(power);
        comboAmplifier.setForGuitar(forGuitar);
        comboAmplifier.setRegulators(regulators);
        comboAmplifier.setInputs(inputs);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        comboAmplifier.setFirstImage(linkToImage);



        сomboAmplifierService.save(comboAmplifier);


        return "redirect:/adminPage/comboAmplifier";
    }

}
