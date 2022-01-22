package com.example.ShopUp.controller;


import com.example.ShopUp.model.ImageOfProduct;
import com.example.ShopUp.model.Mediator;
import com.example.ShopUp.service.ImageService;
import com.example.ShopUp.service.MediatorService;
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
public class MediatorController {
    private final MediatorService mediatorService;
    private final ImageService imageService;

    MediatorController(MediatorService mediatorService , ImageService imageService){
        this.mediatorService =mediatorService;
        this.imageService =imageService;
    }

    @GetMapping("/adminPage/addMediator")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addMediator()
    {
        return "addMediator";
    }

    @PostMapping("/adminPage/addMediator")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addMediatorPost(@RequestParam String nameOfProduct,@RequestParam BigDecimal price ,
                                @RequestParam int quantity ,@RequestParam String descriptions ,
                                @RequestParam String manufacturer, @RequestParam String color ,
                                @RequestParam double thickness ,@RequestParam String material ,
                                @RequestParam String linkToImage) {

        Mediator mediator = new Mediator(nameOfProduct,price, quantity , descriptions , manufacturer,color, thickness,
                material);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        mediator.getImageOfProducts().add(0,imageOfProduct);

        mediatorService.saveMediator(mediator);


        return "redirect:/adminPage/mediator";
    }


    @GetMapping("/adminPage/mediator")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String mediatorAdmin(Model model) {


        Iterable<Mediator> mediators = mediatorService.findAll();

        model.addAttribute("mediators" , mediators);
        return "MediatorAdmin";
    }

    @GetMapping("/Mediator")
    public String mediator(Model model) {
        List<Mediator> mediators = mediatorService.findAllShowAndInQuality();
        List<Mediator> mediatorsNoQuality = mediatorService.findAllShowAndInNoQuality();
        model.addAttribute("mediators" , mediators);
        model.addAttribute("mediatorsNoQuality" , mediatorsNoQuality);
        return "Mediator";
    }

    @GetMapping("/adminPage/showMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        Mediator mediator = mediatorService.findById(id);
        mediator.setShow(true);
        mediatorService.saveMediator(mediator);
        return "redirect:/adminPage/mediator";
    }

    @GetMapping("/aboutMediator{id}")
    public String guitarId(@PathVariable(value = "id") long id , Model model) {
        Mediator mediator = mediatorService.findById(id);

        model.addAttribute("mediator",mediator);
        return "aboutMediator";
    }

    @GetMapping("/adminPage/dontShowMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        Mediator mediator = mediatorService.findById(id);
        mediator.setShow(false);
        mediatorService.saveMediator(mediator);
        return "redirect:/adminPage/mediator";
    }

    @GetMapping("/adminPage/editMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editMediator(@PathVariable(value = "id") long id ,
                               Model model) {
        Mediator mediator = mediatorService.findById(id);
        model.addAttribute("mediator" ,mediator );
        return "editMediator";
    }



    @PostMapping("/adminPage/editMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editMediatorPost(@PathVariable(value = "id") long id,
                                   @RequestParam String nameOfProduct,@RequestParam BigDecimal price ,
                                   @RequestParam int quantity ,@RequestParam String descriptions ,
                                   @RequestParam String manufacturer, @RequestParam String color ,
                                   @RequestParam double thickness ,@RequestParam String material ,
                                   @RequestParam String linkToImage) {
        Mediator mediator = mediatorService.findById(id);



        mediator.setNameOfProduct(nameOfProduct);
        mediator.setPrice(price);
        mediator.setQuantity(quantity);
        mediator.setDescriptions(descriptions);
        mediator.setManufacturer(manufacturer);
        mediator.setThickness(thickness);
        mediator.setColor(color);
        mediator.setMaterial(material);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        mediator.setFirstImage(linkToImage);



        mediatorService.saveMediator(mediator);


        return "redirect:/adminPage/mediator";
    }


    @GetMapping("/adminPage/editQuanityMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost( @PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        Mediator mediator = mediatorService.findById(id);
        mediator.editQuantity(quantityEdit);
        mediatorService.saveMediator(mediator);


        return "redirect:/adminPage/mediator";
    }
}
