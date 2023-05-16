package com.repository.picbox.controllers;

import com.repository.picbox.model.User;
import com.repository.picbox.services.ImageService;
import com.repository.picbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String showUserProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentUser",userService.getUserById(id));
        return "/logged-user/profile";
    }


    @GetMapping("/userSettings/{id}")
    public String showUserSettings(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentUser",userService.getUserById(id));
        return "/logged-user/profile-settings";
    }

    @PostMapping("/api/userSettings/account/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("currentUser") User user, Model model) throws Exception {
        try{
            User existingUser = userService.getUserById(id);
            existingUser.setId(id);
            existingUser.setUsername(user.getUsername());
            existingUser.setEmail(user.getEmail());
            userService.updateUser(existingUser);
        }catch (Exception e){
            return "redirect:/userSettings/"+id;
        }
        return "redirect:/userSettings/"+id;
    }

    @GetMapping("/userSettings/general/{id}")
    public String showUserGeneralSettings(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentUser", userService.getUserById(id));
        return "logged-user/profile-settings-general";
    }

    @PostMapping("/api/userSettings/general/{id}")
    public String updateUserGeneral(@PathVariable("id") Long id, @ModelAttribute("currentUser") User user, Model model) throws Exception {
        try{
            User existingUser = userService.getUserById(id);
            existingUser.setId(id);
            existingUser.setFullname(user.getFullname());
            existingUser.setAboutYou(user.getAboutYou());
            existingUser.setPhoneNumber(user.getPhoneNumber());
            userService.updateUser(existingUser);
        }catch (Exception e){
            return "redirect:/userSettings/general/"+id;
        }
        return "redirect:/userSettings/general/"+id;
    }

    @GetMapping("/userSettings/box/{id}")
    public String showUserBoxSettings(@PathVariable("id") Long id, Model model){
        model.addAttribute("currentUser", userService.getUserById(id));
        return "logged-user/profile-settings-box";
    }

    @PostMapping("/api/userSettings/box/{id}")
    public String updateUserBox(@PathVariable("id") Long id, @ModelAttribute("currentUser") User user, Model model) throws Exception {
        try{
            User existingUser = userService.getUserById(id);
            existingUser.setId(id);
            existingUser.setBoxName(user.getBoxName());
            existingUser.setBoxDescription(user.getBoxDescription());
            userService.updateUser(existingUser);
        }catch (Exception e){
            return "redirect:/userSettings/box/"+id;
        }
        return "redirect:/userSettings/box/"+id;
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
