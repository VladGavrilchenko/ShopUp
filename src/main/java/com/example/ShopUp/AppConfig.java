package com.example.ShopUp;

import com.example.ShopUp.model.*;
import com.example.ShopUp.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.math.BigDecimal;
import java.util.Collections;

@Configuration
public class AppConfig  {





    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner demo(final UserService userService,
                                  final PasswordEncoder encoder , final GuitarService guitarService,
                                  final GuitarPedalService guitarPedalService, final GuitarStrapService guitarStrapService,
                                  final GuitarStringsService guitarStringsService , final GuitarСaseService guitarСaseService ,
                                  final InstrumentCableService instrumentCableService , final  MediatorService mediatorService,
                                  final СomboAmplifierService сomboAmplifierService, final ImageService imageService,
                                  final OrderService orderService , final  TextInPageService textInPageService) {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
             User user = new User("1",encoder.encode("1"),"","","","");
                user.setRoles(Collections.singleton(Role.ADMIN));
             userService.save(user);

             BigDecimal price = new BigDecimal(100);




                for (int i = 0; i < 10; i++) {

                    ImageOfProduct imageOfProduct = new ImageOfProduct("https://cdn-icons-png.flaticon.com/512/126/126515.png");
                    imageService.addImage(imageOfProduct);
                    Guitar guitar = new Guitar("Guitar " + i, price, i, "---", "gigiBscon", "electric"
                            , "Red", "Red tree", "single", 22, 25, "1-volume ,2-timbre, 5-way pickup switch");


                    guitar.getImageOfProducts().add(0,imageOfProduct);
                    guitarService.saveGuitar(guitar);

                    GuitarPedal guitarPedal = new GuitarPedal("Pedal " +i,price,i , "" ,"somewhere in Indonesia",
                            "electric" ,"Over Drive","analog","stompboxes");
                    guitarPedal.getImageOfProducts().add(0,imageOfProduct);
                    guitarPedalService.saveGuitarPedal(guitarPedal);


                    GuitarStrap guitarStrap = new GuitarStrap("Strap " +i , price ,i , "-" , "chin chan chon chin",
                            "rainbow","polypropylene",2,2,2);

                    guitarStrap.getImageOfProducts().add(0,imageOfProduct);
                    guitarStrapService.saveGuitarStrap(guitarStrap);

                    GuitarString guitarString = new GuitarString("String " +i,price , i , "-" ,
                            "D'ADDARIO","nickel-plated steel" ,"0.11-0.49");

                    guitarString.getImageOfProducts().add(0,imageOfProduct);
                    guitarStringsService.save(guitarString);

                        GuitarСase guitarСase = new GuitarСase("Case " +i , price ,i, "-" ,
                                "SIGMA SB-C" , "electric" , "Black" ,2);
                        guitarСase.getImageOfProducts().add(0,imageOfProduct);
                    guitarСaseService.saveGuitarСase(guitarСase);

                    InstrumentCable instrumentCable = new InstrumentCable("Cable " +i , price ,i , "-" ,
                            "SoundKing" ,"jack" ,"Black" ,10);

                    instrumentCable.getImageOfProducts().add(0,imageOfProduct);
                    instrumentCableService.saveInstrumentCable(instrumentCable);

                    Mediator mediator = new Mediator("Mediator "+i , price , i ,"-" ,
                            "zavod Barvinok","blue",1,"plastic");
                    mediator.getImageOfProducts().add(0,imageOfProduct);
                    mediatorService.saveMediator(mediator);

                    ComboAmplifier comboAmplifier = new ComboAmplifier("Combo-amplifier" +i ,price ,i ,"-",
                            "Chord",150,"electric","Drive, Bass, Mid, Treble, Vol" ,"jack");

                    comboAmplifier.getImageOfProducts().add(0, imageOfProduct);
                    сomboAmplifierService.save(comboAmplifier);


                    user =new User("Manager"+i,encoder.encode("1111"), "1262239528"+i ,
                            "matrix@matrix" ,"Smith" ,"Agent " + i);
                    userService.addManager(user);

                    user =new User("Shopper"+i,encoder.encode("1111"), "1262239528"+i ,
                            "WhiteStripes@ukr.net" ,"Jack" ,"White " + i);
                    userService.addUser(user);

                    Order order = new Order(user);
                    order.addGuitar(guitar);
                    order.addGuitarPedal(guitarPedal);
                    order.addInstrumentCable(instrumentCable);
                    order.addComboAmplifier(comboAmplifier);
                    order.addGuitarStraps(guitarStrap);
                    order.addGuitarString(guitarString);
                    order.addMediator(mediator);
                    order.addGuitarСase(guitarСase);
                    order.setProcess(false);
                    orderService.save(order);


                }

                TextInPage textInPage = new TextInPage("AboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAboutAbout","about");
                textInPageService.save(textInPage);

                textInPage = new TextInPage("12622395282","contactPhone");
                textInPageService.save(textInPage);

                textInPage = new TextInPage("guitarHouse@gigimail.com","contactEmail");
                textInPageService.save(textInPage);





            }
        };
    }
}