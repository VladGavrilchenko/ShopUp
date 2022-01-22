package com.example.ShopUp.controller;

import com.example.ShopUp.model.*;
import com.example.ShopUp.service.OrderService;
import com.example.ShopUp.service.TextInPageService;
import com.example.ShopUp.service.UserService;
import com.example.ShopUp.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    private final UserService userService;
    private final TextInPageService textInPageService;
    private final OrderService orderService;

    AdminController(UserService userService, TextInPageService textInPageService ,OrderService orderService)
    {
        this.userService = userService;
        this.textInPageService =textInPageService;
        this.orderService =orderService;
    }

    @GetMapping("/adminPage")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String toAdminPage()
    {
       return "adminPage";
    }


    @GetMapping("/adminPage/Team")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String toTeamPage(Model model) {

        Iterable<User> managers = userService.findAllManager();


        model.addAttribute("managers",managers);
        return "Team";
    }


    @GetMapping("/adminPage/orders")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String toOrdersPage(Model model) {

        Iterable<Order> orders = orderService.getAllOrdersNotProgres();


        model.addAttribute("orders",orders);
        return "Orders";
    }

    @GetMapping("/adminPage/Order{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String toOrderPage(@PathVariable(value = "id") long id, Model model) {


        Order orderDB = orderService.getOrderById(id);
        Map<Guitar, Integer> guitarIntegerMap = orderDB.getGuitars().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("guitars",guitarIntegerMap);
        Map<GuitarPedal, Integer> guitarPedalIntegerMap = orderDB.getGuitarPedals().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("guitarPedals",guitarPedalIntegerMap);
        Map<GuitarStrap, Integer> guitarStrapIntegerMap = orderDB.getGuitarStraps().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("guitarStrap",guitarStrapIntegerMap);
        Map<ComboAmplifier, Integer> comboAmplifierIntegerMap = orderDB.getComboAmplifiers().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("comboAmplifier",comboAmplifierIntegerMap);
        Map<GuitarString, Integer> guitarStringIntegerMap = orderDB.getGuitarStrings().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("guitarString",guitarStringIntegerMap);
        Map<Mediator, Integer> mediatorIntegerMap  = orderDB.getMediators().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("mediators",mediatorIntegerMap);

        Map<GuitarСase, Integer> guitarСaseIntegerMap  = orderDB.getGuitarCases().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("guitarCase",guitarСaseIntegerMap);
        Map<InstrumentCable, Integer> instrumentCableIntegerMap  = orderDB.getInstrumentCables().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        model.addAttribute("instrumentCable",instrumentCableIntegerMap);


        return "aboutOrder";
    }

    @GetMapping("/adminPage/OrderDone{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String toOrderDone(@PathVariable(value = "id") long id) {


        Order order = orderService.getOrderById(id);
        order.setDone(true);
        orderService.save(order);


        return "redirect:/adminPage/orders";
    }

    @GetMapping("/adminPage/OrderDontDone{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String orderDontDone(@PathVariable(value = "id") long id) {


        Order order = orderService.getOrderById(id);
        order.setDone(false);
        orderService.save(order);


        return "redirect:/adminPage/orders";
    }


    @GetMapping("/adminPage/OrdersDone")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String toOrderDone( Model model) {


        Iterable<Order> orders = orderService.getAllOrdersOnProgresDone();


        model.addAttribute("orders",orders);
        return "Orders";
    }



    @GetMapping("/adminPage/EditAbout")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editAbout( Model model){

        TextInPage about = textInPageService.getAbout();
        model.addAttribute("about" ,about );
        return "editAbout";
    }


    @PostMapping("/adminPage/EditAbout")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editAboutPost(@RequestParam String about){

        TextInPage textInPage = textInPageService.getAbout();
        textInPage.setText(about);
        textInPageService.save(textInPage);
        return "redirect:/adminPage";
    }


    @GetMapping("/adminPage/EditContact")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editContact( Model model){

        TextInPage email = textInPageService.getContactEmail();
        TextInPage phone = textInPageService.getContactPhone();
        model.addAttribute("email" ,email );
        model.addAttribute("phone" ,phone );
        return "editContact";
    }


    @PostMapping("/adminPage/EditContact")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MANAGER')")
    public String editContactPost(@RequestParam String email,@RequestParam String phone){

        TextInPage emailText = textInPageService.getContactEmail();
        TextInPage phoneText = textInPageService.getContactPhone();
        emailText.setText(email);
        phoneText.setText(phone);
        textInPageService.save(emailText);
        textInPageService.save(phoneText);
        return "redirect:/adminPage";
    }
}
