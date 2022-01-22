package com.example.ShopUp.controller;

import com.example.ShopUp.model.*;
import com.example.ShopUp.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController {

    private final GuitarService guitarService;
    private final GuitarPedalService guitarPedalService;
    private final GuitarStrapService guitarStrapService;
    private final GuitarStringsService guitarStringsService;
    private final GuitarСaseService guitarСaseService;
    private final InstrumentCableService instrumentCableService;
    private final MediatorService mediatorService;
    private final  СomboAmplifierService сomboAmplifierService;
    private final TextInPageService textInPageService;


    MainController(GuitarService guitarService ,GuitarPedalService guitarPedalService,
                   GuitarStrapService guitarStrapService, GuitarStringsService guitarStringsService ,
                   GuitarСaseService guitarСaseService , InstrumentCableService instrumentCableService ,
                   MediatorService mediatorService ,СomboAmplifierService сomboAmplifierService ,
                   TextInPageService textInPageService){
        this.guitarService = guitarService;
        this.guitarPedalService =guitarPedalService;
        this.guitarStrapService =guitarStrapService;
        this.guitarStringsService =guitarStringsService;
        this.guitarСaseService =guitarСaseService;
        this.instrumentCableService =instrumentCableService;
        this.mediatorService =mediatorService;
        this.сomboAmplifierService =сomboAmplifierService;
        this.textInPageService =textInPageService;
    }





    @GetMapping("/")
    public String main(Model model) {
        Iterable<Guitar> guitars =guitarService.findAllShowAndInQuality();
        Iterable<GuitarPedal> guitarPedals =guitarPedalService.findAllShowAndInQuality();
        Iterable<GuitarStrap> guitarStraps = guitarStrapService.findAllShowAndInQuality();
        Iterable<GuitarString> guitarStrings = guitarStringsService.findAllShowAndInQuality();
        Iterable<GuitarСase> guitarСases =guitarСaseService.findAllShowAndInQuality();
        Iterable<Mediator> mediators = mediatorService.findAllShowAndInQuality();
        Iterable<InstrumentCable> instrumentCables = instrumentCableService.findAllShowAndInQuality();
        Iterable<ComboAmplifier> сomboAmplifiers = сomboAmplifierService.findAllShowAndInQuality();
        model.addAttribute("guitars",guitars);
        model.addAttribute("guitarPedals",guitarPedals);
        model.addAttribute("guitarStraps",guitarStraps);
        model.addAttribute("guitarStrings",guitarStrings);
        model.addAttribute("guitarCases",guitarСases);
        model.addAttribute("instrumentCables",instrumentCables);
        model.addAttribute("mediators",mediators);
        model.addAttribute("comboAmplifiers",сomboAmplifiers);
        return "main";
    }


    @GetMapping("/about")
    public String about(Model model) {

        TextInPage about = textInPageService.getAbout();
        TextInPage phone = textInPageService.getContactPhone();
        TextInPage email = textInPageService.getContactEmail();
        model.addAttribute("about" ,about);
        model.addAttribute("phone" ,phone);
        model.addAttribute("email" ,email);
        return "about";
    }


    @GetMapping("/contact")
    public String contact(Model model) {


        TextInPage phone = textInPageService.getContactPhone();
        TextInPage email = textInPageService.getContactEmail();
        model.addAttribute("phone" ,phone);
        model.addAttribute("email" ,email);
        return "contact";
    }







}
