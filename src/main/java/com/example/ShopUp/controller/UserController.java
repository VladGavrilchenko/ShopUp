package com.example.ShopUp.controller;

import com.example.ShopUp.User;
import com.example.ShopUp.model.Order;
import com.example.ShopUp.service.OrderService;
import com.example.ShopUp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@Controller
public class UserController {
    private  final UserService userService;
    private  final  PasswordEncoder passwordEncoder;
    private  final OrderService orderService;

    UserController(UserService userService ,PasswordEncoder passwordEncoder ,OrderService orderService)
    {
        this.userService = userService;
        this.passwordEncoder =passwordEncoder;
        this.orderService =orderService;
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, @RequestParam String  firsName , @RequestParam String lastName,
                          @RequestParam String phone , @RequestParam String email , Map<String, Object> model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }


        user.setEmail(email);
        user.setPhone(phone);
        user.setFirstName(firsName);
        user.setLastName(lastName);
        user.setActive(true);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);


        return "redirect:/login";
    }


    @GetMapping("/registrationManager")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String registrationManager() {
        return "registrationManager";
    }

    @PostMapping("/registrationManager")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addManager(User user, @RequestParam String  firsName , @RequestParam String lastName,
                          @RequestParam String phone , @RequestParam String email , Map<String, Object> model) {
        User userFromDb = userService.findUserByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registrationManager";
        }


        user.setEmail(email);
        user.setPhone(phone);
        user.setFirstName(firsName);
        user.setLastName(lastName);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userService.addManager(user);


        return "redirect:/adminPage/Team";
    }



    @GetMapping("/editUser")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String editUser(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user ,
                           Model model) {
        User userFroDB = userService.findUserByUsername(user.getUsername());
        model.addAttribute("user" ,userFroDB );
        return "editUser";
    }

    @GetMapping("/UserInf")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String UserInf(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user , Model model) {

        User userFroDB = userService.findUserByUsername(user.getUsername());
        Iterable<Order> orders = orderService.getAllOrdersByUser(userFroDB);
        model.addAttribute("orders" ,orders );
        model.addAttribute("user" ,userFroDB );


        return "UserInf";
    }



    @PostMapping("/editUser")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String editUserPost(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                               @RequestParam String  firsName , @RequestParam String lastName,
                             @RequestParam String phone , @RequestParam String email ) {
        User userFromDb = userService.findUserByUsername(user.getUsername());



        userFromDb.setEmail(email);
        userFromDb.setPhone(phone);
        userFromDb.setFirstName(firsName);
        userFromDb.setLastName(lastName);



        userService.save(userFromDb);


        return "redirect:/UserInf";
    }


    @GetMapping("/editPassword")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String editPassword() {
        return "editPassword";
    }


    @PostMapping("/editPassword")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public String editPasswordPost(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user,
                               @RequestParam String password ,  @RequestParam String passwordToVerify ,
                                   Map<String, Object> model ) {


        if(password.equals(passwordToVerify)== false){
            model.put("message", "Passwords do not match");
            return "editPassword";}
        User userFromDb = userService.findUserByUsername(user.getUsername());

        userFromDb.setPassword(passwordEncoder.encode(password));
        userService.save(userFromDb);


        return "redirect:/UserInf";
    }



    @GetMapping("/adminPage/Team/editManagers{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editManagers(@PathVariable(value = "id") long id ,
                           Model model) {
        User userFromDb = userService.findById(id);
        model.addAttribute("user" ,userFromDb );
        return "editManagers";
    }


    @GetMapping("/adminPage/Team/deleteManager{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteManager(@PathVariable(value = "id") long id ) {
        User userFromDb = userService.findById(id);
        userService.delete(userFromDb);
        return "redirect:/adminPage/Team";
    }



    @PostMapping("/adminPage/Team/editManagers{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editManagersPost(@PathVariable(value = "id") long id,
                               @RequestParam String  firsName , @RequestParam String lastName,
                               @RequestParam String phone , @RequestParam String email , Map<String, Object> model) {
        User userFromDb = userService.findById(id);



        userFromDb.setEmail(email);
        userFromDb.setPhone(phone);
        userFromDb.setFirstName(firsName);
        userFromDb.setLastName(lastName);



        userService.save(userFromDb);


        return "redirect:/adminPage/Team";
    }
}
