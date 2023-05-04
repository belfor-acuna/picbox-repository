package com.repository.picbox.controllers;

import com.repository.picbox.model.LoginDTO;
import com.repository.picbox.model.RegisterDTO;
import com.repository.picbox.model.User;
import com.repository.picbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("*")
    public String initialPage(){
        return "redirect:/gallery";
    }

    @GetMapping("/gallery")
    public String gallery(){
        return "landing-page";
    }


    @GetMapping("/signup")
    public String signupView(Model model){
        model.addAttribute("registerDTO",new RegisterDTO());
        return "signup";
    }

    @PostMapping("/api/signup")
    public String registerUser(RegisterDTO registerDTO, Model model){
        try{
            service.registerUser(registerDTO);
        }catch (Exception e){
            return "redirect:/signup";
        }
        return "redirect:/gallery";
    }

    @GetMapping("/showUsers")
    public String listUsers(Model model){
        model.addAttribute("users", service.listUsers());
        return "admin/show-users-view";
    }

    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("loginDTO", new LoginDTO());
        return "login";
    }

    @PostMapping("/api/login")
    public String loginUser(LoginDTO loginDTO, Model model){
        try {
            String rol = service.loginUser(loginDTO);
            if (rol == "Administrador")return "showUsers";

            if (rol == "Usuario")return "gallery";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/login";
        }
        return "redirect:/showUsers";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model){
        model.addAttribute("user",service.getUserById(id));
        return "edit-user";
    }

    @PostMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User user, Model model) throws Exception {
        try{
            User existingUser = service.getUserById(id);
            existingUser.setId(id);
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            existingUser.setFullname(user.getFullname());
            service.updateUser(existingUser);
        }catch (Exception e){
            return "redirect:/showUsers";
        }
        return "redirect:/showUsers";
    }

    @GetMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id){
        service.deleteUser(id);
        return "redirect:/showUsers";
    }

    @GetMapping("/upload-image")
    public String showUploadImageView(){
        return "redirect:/upload-image";
    }

}
