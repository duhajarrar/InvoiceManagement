package com.example.invoicemanagement.controller;

import com.example.invoicemanagement.model.User;
import com.example.invoicemanagement.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;
    //private CustomerService customerService;
    public RegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("users")
    public User UserRegistration() {
        return new User();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("users") User registration) {
        userService.save(registration);
        //customerService.save(registration);
        return "redirect:/registration?success";
        //return "redirect:/login";

    }
}
//
//package com.example.invoicemanagement.controller;
//
//import com.example.invoicemanagement.model.User;
//import com.example.invoicemanagement.model.Customer;
//import com.example.invoicemanagement.model.User;
////import com.example.invoicemanagement.service.CustomerService;
//import com.example.invoicemanagement.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@SessionAttributes("user")
//@RequestMapping("/registration")
//public class RegistrationController {
//
//    private UserService userService;
//  //  private CustomerService customerService;
//
//    //@GetMapping("/registration")
//    @RequestMapping(value = "/registration",method = RequestMethod.GET)
//    public String Registration() {
//        System.out.println("Signup form opened .. ");
//        return "registration";
//    }
//
//    //@PostMapping
//    @RequestMapping(value = "/registration",method = RequestMethod.POST)
//    public String registerUserAccount(@ModelAttribute("users") User user) {
//        if(userService.findByEmail(user.getEmail())!=null)
//        {
//            System.out.println("Email exist");
//
//        }else{
//            System.out.println("User signup done");
//            userService.save(user);
//        }
//        //return "redirect:/registration?success";
//        return "redirect:/login";
//
//    }
//}
