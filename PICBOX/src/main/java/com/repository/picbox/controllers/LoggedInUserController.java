package com.repository.picbox.controllers;

import com.repository.picbox.services.ImageService;
import com.repository.picbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LoggedInUserController {

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @GetMapping("/userGallery/{id}")
    public String showLoggedInGallery(@PathVariable("id") Long id, Model model){
        model.addAttribute("images", imageService.listImages());
        model.addAttribute("currentUser",userService.getUserById(id));
        return "/logged-user/logged-in-gallery";
    }


    @GetMapping("/userProfile/{id}")
    public String showLoggedInProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentUser",userService.getUserById(id));
        return "/logged-user/profile";
    }

}
