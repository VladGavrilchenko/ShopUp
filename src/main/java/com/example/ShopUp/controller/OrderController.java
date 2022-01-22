package com.example.ShopUp.controller;

import com.example.ShopUp.User;
import com.example.ShopUp.model.*;
import com.example.ShopUp.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final GuitarService guitarService;
    private final GuitarPedalService guitarPedalService;
    private final GuitarStrapService guitarStrapService;
    private final GuitarStringsService guitarStringsService;
    private final GuitarСaseService guitarСaseService;
    private final InstrumentCableService instrumentCableService;
    private final MediatorService mediatorService;
    private final  СomboAmplifierService сomboAmplifierService;


    OrderController(OrderService orderService, UserService userService,GuitarService guitarService ,
                    GuitarPedalService guitarPedalService,GuitarStrapService guitarStrapService,
                    GuitarStringsService guitarStringsService,GuitarСaseService guitarСaseService,
     InstrumentCableService instrumentCableService ,MediatorService mediatorService,
                    СomboAmplifierService сomboAmplifierService){
        this.orderService =orderService;
        this.userService =userService;
        this.guitarService =guitarService;
        this.guitarPedalService =guitarPedalService;
        this.guitarStrapService =guitarStrapService;
        this.guitarStringsService =guitarStringsService;
        this.guitarСaseService =guitarСaseService;
        this.instrumentCableService= instrumentCableService;
        this.mediatorService =mediatorService;
        this.сomboAmplifierService =сomboAmplifierService;
    }


    @GetMapping("/addToOrderGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addGuitarToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                   @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        Guitar guitar = guitarService.findById(id);

        orderDB.addGuitar(guitar);

        guitarService.saveGuitar(guitar);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }


    @GetMapping("/Order{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String orderUser(@PathVariable(value = "id") long id , Model model){

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

        return "aboutOrderUser";
    }

    @GetMapping("/addToOrderGuitarCase{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addGuitarCaseToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                   @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        GuitarСase guitarСase =guitarСaseService.findById(id);
        orderDB.addGuitarСase(guitarСase);

        guitarСaseService.saveGuitarСase(guitarСase);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }


    @GetMapping("/addToOrderMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addMediatorToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                       @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        Mediator mediator =mediatorService.findById(id);

        orderDB.addMediator(mediator);

        mediatorService.saveMediator(mediator);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/addToOrderGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addGuitarPedalToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                     @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        GuitarPedal guitarPedal =guitarPedalService.findById(id);

        orderDB.addGuitarPedal(guitarPedal);

        guitarPedalService.saveGuitarPedal(guitarPedal);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }


    @GetMapping("/addToOrderGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addGuitarStrapToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                        @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        GuitarStrap guitarStrap =guitarStrapService.findById(id);

        orderDB.addGuitarStraps(guitarStrap);

        guitarStrapService.saveGuitarStrap(guitarStrap);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }


    @GetMapping("/addToOrderGuitarStrings{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addGuitarStringsToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                        @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        GuitarString guitarString =guitarStringsService.getById(id);

        orderDB.addGuitarString(guitarString);

        guitarStringsService.save(guitarString);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/addToOrderInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addInstrumentCableToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                          @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        InstrumentCable instrumentCable =instrumentCableService.findById(id);

        orderDB.addInstrumentCable(instrumentCable);

        instrumentCableService.saveInstrumentCable(instrumentCable);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/addToOrderComboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String addComboAmplifierToOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                            @PathVariable(value = "id") long id,HttpServletRequest request ){
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        ComboAmplifier comboAmplifier =сomboAmplifierService.getById(id);

        orderDB.addComboAmplifier(comboAmplifier);

        сomboAmplifierService.save(comboAmplifier);
        orderService.save(orderDB);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

    @GetMapping("/basket")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String toBasket(Model model,@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        String messages ="";
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        model.addAttribute("order",orderDB);

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

        for (Map.Entry<InstrumentCable, Integer> entry : instrumentCableIntegerMap.entrySet()) {
            InstrumentCable instrumentCable = instrumentCableService.findById(entry.getKey().getId());
            if(instrumentCable.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - instrumentCable.getQuantity();
                 messages = "Difference between stock and order " + difference +" of "
                        + instrumentCable.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }

        for (Map.Entry<GuitarСase, Integer> entry : guitarСaseIntegerMap.entrySet()) {
            GuitarСase guitarСase = guitarСaseService.findById(entry.getKey().getId());
            if(guitarСase.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - guitarСase.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + guitarСase.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }

        for (Map.Entry<Mediator, Integer> entry : mediatorIntegerMap.entrySet()) {
            Mediator mediator = mediatorService.findById(entry.getKey().getId());
            if(mediator.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - mediator.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + mediator.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }

        for (Map.Entry<GuitarString, Integer> entry : guitarStringIntegerMap.entrySet()) {
            GuitarString guitarString = guitarStringsService.getById(entry.getKey().getId());
            if(guitarString.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - guitarString.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + guitarString.getNameOfProduct() +" ,we can't fulfill the order";


            }
        }
        for (Map.Entry<Guitar, Integer> entry : guitarIntegerMap.entrySet()) {
            Guitar guitarFromDB = guitarService.findById(entry.getKey().getId());
            if(guitarFromDB.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - guitarFromDB.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + guitarFromDB.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }

        for (Map.Entry<ComboAmplifier, Integer> entry : comboAmplifierIntegerMap.entrySet()) {
            ComboAmplifier comboAmplifier = сomboAmplifierService.getById(entry.getKey().getId());
            if(comboAmplifier.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - comboAmplifier.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + comboAmplifier.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }

        for (Map.Entry<GuitarPedal, Integer> entry : guitarPedalIntegerMap.entrySet()) {
            GuitarPedal guitarPedalFromDB = guitarPedalService.findById(entry.getKey().getId());
            if(guitarPedalFromDB.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - guitarPedalFromDB.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + guitarPedalFromDB.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }

        for (Map.Entry<GuitarStrap, Integer> entry : guitarStrapIntegerMap.entrySet()) {
            GuitarStrap guitarStrap = guitarStrapService.findById(entry.getKey().getId());
            if(guitarStrap.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - guitarStrap.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + guitarStrap.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }

        for (Map.Entry<GuitarPedal, Integer> entry : guitarPedalIntegerMap.entrySet()) {
            GuitarPedal guitarPedalFromDB = guitarPedalService.findById(entry.getKey().getId());
            if(guitarPedalFromDB.getQuantity() < entry.getValue() ){
                int difference = entry.getValue() - guitarPedalFromDB.getQuantity();
                messages = "Difference between stock and order " + difference +" of "
                        + guitarPedalFromDB.getNameOfProduct() +" ,we can't fulfill the order";



            }
        }


        model.addAttribute("messages",messages);



        return "basket";
    }

    @GetMapping("/addGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addGuitarToBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                    @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        Guitar guitar = guitarService.findById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addGuitar(guitar);

        orderService.save(orderDB);
        return "redirect:/basket";
    }


    @GetMapping("/deleteGuitar{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteGuitarBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                    @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Guitar guitar = guitarService.findById(id);
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        orderDB.subtractOfOrder(guitar.getPrice());
        orderDB.getGuitars().remove(guitar);




        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/addGuitarStrings{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addGuitarStringsBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                    @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        GuitarString guitarString = guitarStringsService.getById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addGuitarString(guitarString);

        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/deleteGuitarStrings{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteGuitarStrings(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                     @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        GuitarString guitarString = guitarStringsService.getById(id);
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        orderDB.subtractOfOrder(guitarString.getPrice());
        orderDB.getGuitarStrings().remove(guitarString);




        orderService.save(orderDB);
        return "redirect:/basket";
    }


    @GetMapping("/addMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addMediatorBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                         @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        Mediator mediator = mediatorService.findById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addMediator(mediator);

        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/deleteMediator{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteMediator(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                      @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
       Mediator mediator = mediatorService.findById(id);
       orderDB.subtractOfOrder(mediator.getPrice());
       orderDB.getMediators().remove(mediator);




        orderService.save(orderDB);
        return "redirect:/basket";
    }


    @GetMapping("/addInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addInstrumentCableBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                    @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        InstrumentCable instrumentCable = instrumentCableService.findById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addInstrumentCable(instrumentCable);

        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/deleteInstrumentCable{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteInstrumentCable(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                 @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        InstrumentCable instrumentCable = instrumentCableService.findById(id);
        orderDB.subtractOfOrder(instrumentCable.getPrice());
        orderDB.getInstrumentCables().remove(instrumentCable);




        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/addGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addGuitarStrapBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                           @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        GuitarStrap guitarStrap = guitarStrapService.findById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addGuitarStraps(guitarStrap);

        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/deleteGuitarStrap{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteGuitarStrap(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                        @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        GuitarStrap guitarStrap = guitarStrapService.findById(id);
        orderDB.subtractOfOrder(guitarStrap.getPrice());
        orderDB.getGuitarStraps().remove(guitarStrap);





        orderService.save(orderDB);
        return "redirect:/basket";
    }


    @GetMapping("/addСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addСomboAmplifierBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                       @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addComboAmplifier(comboAmplifier);

        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/deleteСomboAmplifier{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteСomboAmplifier(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                    @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        ComboAmplifier comboAmplifier = сomboAmplifierService.getById(id);
        orderDB.subtractOfOrder(comboAmplifier.getPrice());
        orderDB.getComboAmplifiers().remove(comboAmplifier);




        orderService.save(orderDB);
        return "redirect:/basket";
    }


    @GetMapping("/addGuitarCase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addGuitarCaseBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                          @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        GuitarСase guitarСase = guitarСaseService.findById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addGuitarСase(guitarСase);

        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/deleteGuitarCase{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteGuitarCase(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                       @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        GuitarСase guitarСase = guitarСaseService.findById(id);
        orderDB.subtractOfOrder(guitarСase.getPrice());
        orderDB.getGuitarCases().remove(guitarСase);




        orderService.save(orderDB);
        return "redirect:/basket";
    }


    @GetMapping("/addGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String addGuitarPedalBasket(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                      @PathVariable(value = "id") long id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        GuitarPedal guitarPedal = guitarPedalService.findById(id);
        if(orderDB == null){
            orderDB = new Order(userFromDB);
        }

        orderDB.addGuitarPedal(guitarPedal);

        orderService.save(orderDB);
        return "redirect:/basket";
    }

    @GetMapping("/deleteGuitarPedal{id}")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String deleteGuitarPedal(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                                   @PathVariable(value = "id") int id  ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);
        GuitarPedal guitarPedal = guitarPedalService.findById(id);
        orderDB.subtractOfOrder(guitarPedal.getPrice());
        orderDB.getGuitarPedals().remove(guitarPedal);




        orderService.save(orderDB);
        return "redirect:/basket";
    }


    @GetMapping("/doneOrder")
    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('USER')")
    public String doneOrder(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user
                                    ) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        Order orderDB = orderService.getOrderOnProgresAndUser(userFromDB);




        Map<Guitar, Integer> guitarIntegerMap = orderDB.getGuitars().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map<GuitarPedal, Integer> guitarPedalIntegerMap = orderDB.getGuitarPedals().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map<GuitarStrap, Integer> guitarStrapIntegerMap = orderDB.getGuitarStraps().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map<ComboAmplifier, Integer> comboAmplifierIntegerMap = orderDB.getComboAmplifiers().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map<GuitarString, Integer> guitarStringIntegerMap = orderDB.getGuitarStrings().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map<Mediator, Integer> mediatorIntegerMap  = orderDB.getMediators().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map<GuitarСase, Integer> guitarСaseIntegerMap  = orderDB.getGuitarCases().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));

        Map<InstrumentCable, Integer> instrumentCableIntegerMap  = orderDB.getInstrumentCables().stream()
                .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));


        for (Map.Entry<InstrumentCable, Integer> entry : instrumentCableIntegerMap.entrySet()) {
            InstrumentCable instrumentCable = instrumentCableService.findById(entry.getKey().getId());
            instrumentCable.editQuantity(-entry.getValue());
            instrumentCableService.saveInstrumentCable(instrumentCable);
        }

        for (Map.Entry<Mediator, Integer> entry : mediatorIntegerMap.entrySet()) {
            Mediator mediator = mediatorService.findById(entry.getKey().getId());
            mediator.editQuantity(-entry.getValue());
            mediatorService.saveMediator(mediator);
        }


        for (Map.Entry<GuitarString, Integer> entry : guitarStringIntegerMap.entrySet()) {
            GuitarString guitarString = guitarStringsService.getById(entry.getKey().getId());
            guitarString.editQuantity(-entry.getValue());
            guitarStringsService.save(guitarString);
        }

        for (Map.Entry<ComboAmplifier, Integer> entry : comboAmplifierIntegerMap.entrySet()) {
            ComboAmplifier comboAmplifier = сomboAmplifierService.getById(entry.getKey().getId());
            comboAmplifier.editQuantity(-entry.getValue());
            сomboAmplifierService.save(comboAmplifier);
        }


        for (Map.Entry<Guitar, Integer> entry : guitarIntegerMap.entrySet()) {
           Guitar guitarFromDB = guitarService.findById(entry.getKey().getId());
            guitarFromDB.editQuantity(-entry.getValue());
            guitarService.saveGuitar(guitarFromDB);
        }

        for (Map.Entry<GuitarPedal, Integer> entry : guitarPedalIntegerMap.entrySet()) {
            GuitarPedal guitarPedalFromDB = guitarPedalService.findById(entry.getKey().getId());
            guitarPedalFromDB.editQuantity(-entry.getValue());
            guitarPedalService.saveGuitarPedal(guitarPedalFromDB);
        }

        for (Map.Entry<GuitarStrap, Integer> entry : guitarStrapIntegerMap.entrySet()) {
            GuitarStrap guitarStrap = guitarStrapService.findById(entry.getKey().getId());
            guitarStrap.editQuantity(-entry.getValue());
            guitarStrapService.saveGuitarStrap(guitarStrap);
        }

        for (Map.Entry<GuitarСase, Integer> entry : guitarСaseIntegerMap.entrySet()) {
            GuitarСase guitarСase = guitarСaseService.findById(entry.getKey().getId());
            guitarСase.editQuantity(-entry.getValue());
            guitarСaseService.saveGuitarСase(guitarСase);
        }

        orderDB.setProcess(false);
        orderService.save(orderDB);
        return "redirect:/basket";
    }

}
