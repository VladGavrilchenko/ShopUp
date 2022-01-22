package com.example.ShopUp.controller;

import com.example.ShopUp.Repository.GuitarStringRepository;
import com.example.ShopUp.model.GuitarString;
import com.example.ShopUp.model.ImageOfProduct;
import com.example.ShopUp.service.GuitarStringsService;
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
public class GuitarStringController {

    private final GuitarStringsService guitarStringsService;
    private final ImageService imageService;


    GuitarStringController( GuitarStringsService guitarStringsService , ImageService imageService){
      this.guitarStringsService =guitarStringsService;
      this.imageService =imageService;

    }


    @GetMapping("/adminPage/addGuitarString")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarString()
    {
        return "addGuitarString";
    }

    @PostMapping("/adminPage/addGuitarString")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String addGuitarStringPost(@RequestParam String nameOfProduct,@RequestParam BigDecimal price ,
                                     @RequestParam int quantity ,@RequestParam String descriptions ,
                                     @RequestParam String manufacturer,@RequestParam String material ,
                                     @RequestParam String caliber, @RequestParam String linkToImage) {

        GuitarString guitarString = new GuitarString(nameOfProduct , price ,quantity , descriptions , manufacturer,
                material ,caliber);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarString.getImageOfProducts().add(0,imageOfProduct);
        guitarStringsService.save(guitarString);


        return "redirect:/adminPage/guitarString";
    }


    @GetMapping("/GuitarStrings")
    public String guitarStrings(Model model) {
        List<GuitarString> guitarStrings = guitarStringsService.findAllShowAndInQuality();
        List<GuitarString> guitarStringsNoQuality = guitarStringsService.findAllShowAndInNoQuality();
        model.addAttribute("guitarStrings" , guitarStrings);
        model.addAttribute("guitarStringsNoQuality" , guitarStringsNoQuality);
        return "GuitarStrings";
    }

    @GetMapping("/aboutGuitarStrings{id}")
    public String guitarStringsId(@PathVariable(value = "id") long id , Model model) {
        GuitarString guitarString = guitarStringsService.getById(id);

        model.addAttribute("guitarString",guitarString);
        return "aboutGuitarStrings";
    }


    @GetMapping("/adminPage/guitarString")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String guitarStringAdmin(Model model)
    {


        Iterable<GuitarString> guitarStrings = guitarStringsService.getAll();

        model.addAttribute("guitarStrings" , guitarStrings);
        return "GuitarStringAdmin";
    }

    @GetMapping("/adminPage/editQuanityGuitarString{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanity() {

        return "editQuanity";
    }

    @PostMapping("/adminPage/editQuanityGuitarString{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editQuanityPost(@PathVariable(value = "id") long id , @RequestParam int quantityEdit) {

        GuitarString guitarString = guitarStringsService.getById(id);
        guitarString.editQuantity(quantityEdit);
        guitarStringsService.save(guitarString);


        return "redirect:/adminPage/guitarString";
    }


    @GetMapping("/adminPage/showGuitarString{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String show(@PathVariable(value = "id") long id) {
        GuitarString guitarString = guitarStringsService.getById(id);
        guitarString.setShow(true);
        guitarStringsService.save(guitarString);
        return "redirect:/adminPage/guitarString";
    }


    @GetMapping("/adminPage/dontShowGuitarString{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String dontShow(@PathVariable(value = "id") long id) {
        GuitarString guitarString = guitarStringsService.getById(id);
        guitarString.setShow(false);
        guitarStringsService.save(guitarString);
        return "redirect:/adminPage/guitarString";
    }


    @GetMapping("/adminPage/editGuitarString{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarString(@PathVariable(value = "id") long id ,
                               Model model) {
        GuitarString guitarString = guitarStringsService.getById(id);
        model.addAttribute("guitarString" ,guitarString );
        return "editGuitarString";
    }



    @PostMapping("/adminPage/editGuitarString{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editGuitarString(@PathVariable(value = "id") long id,
                                   @RequestParam String nameOfProduct,@RequestParam BigDecimal price ,
                                   @RequestParam int quantity ,@RequestParam String descriptions ,
                                   @RequestParam String manufacturer,@RequestParam String material ,
                                   @RequestParam String caliber, @RequestParam String linkToImage) {
        GuitarString guitarString = guitarStringsService.getById(id);



        guitarString.setNameOfProduct(nameOfProduct);
        guitarString.setPrice(price);
        guitarString.setQuantity(quantity);
        guitarString.setDescriptions(descriptions);
        guitarString.setManufacturer(manufacturer);
        guitarString.setMaterial(material);
        guitarString.setCaliber(caliber);
        ImageOfProduct imageOfProduct = new ImageOfProduct(linkToImage);
        imageService.addImage(imageOfProduct);
        guitarString.setFirstImage(linkToImage);



        guitarStringsService.save(guitarString);


        return "redirect:/adminPage/guitarString";
    }

}
