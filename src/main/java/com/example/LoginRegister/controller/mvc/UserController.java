package com.example.LoginRegister.controller.mvc;


import com.example.LoginRegister.controller.service.UserService;
import com.example.LoginRegister.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * http://localhost:8081/  GET
 *                         POST
 *
 *  http://localhost:8081/register  GET -> register.html
 *
 *  http://localhost:8081/saveUser  POST ->
 *
 * <li>1. DispatcherServlet</li>
 * <li>2. HandlerMapper</li>
 * <li>3. Controller</li> -> učitati i podatke preko service
 * <li>4. ViewResolver</li>
 * <li>5. View</li>
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/")
    public String viewUsers(Model model){
        List<User> userList = userService.getAllUsers();
        model.addAttribute("listaKorisnika", userList);
        return "index";
    }

    @GetMapping("/register")
    public String showNewUserForm(Model model){
        User user = new User();
        model.addAttribute("prazanKorisnik", user);
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("prazanKorisnik") User user){
        user.setRole("USER");
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userService.saveUser(user);
        return "redirect:/";
    }
}
