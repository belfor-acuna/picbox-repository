package com.repository.picbox.controllers;

import com.repository.picbox.services.ImageService;
import com.repository.picbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

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

    @GetMapping("/fullview/{userId}/image/{id}")
    public String LoggedInFullViewImage(Model model, @PathVariable("id") Integer id, @PathVariable("userId") Long userId) throws IOException {
        model.addAttribute("currentUser",userService.getUserById(userId));
        model.addAttribute("image",imageService.getImageById(id));
        return "/logged-user/logged-in-full-view";
    }

    @GetMapping("/viewOtherProfile/{currentId}/{profileId}")
    public String viewOtherProfile(Model model, @PathVariable("currentId") Long currentId,@PathVariable("profileId")Long profileId){
        if (currentId==profileId){
            return "redirect:/userProfile/"+currentId;
        }else
            model.addAttribute("currentUser", userService.getUserById(currentId));
            model.addAttribute("userProfile", userService.getUserById(profileId));
            return "/logged-user/view-other-profile";
    }

}
