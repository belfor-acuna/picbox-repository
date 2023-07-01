package com.repository.picbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.repository.picbox.model.Tag;
import com.repository.picbox.services.AdminService;
import com.repository.picbox.services.ImageService;
import com.repository.picbox.services.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @GetMapping("/dashboard")
    public String showAdminDashboard(Model model){
        model.addAttribute("cuentasCreadas", adminService.getCantidadDeUsuarios());
        model.addAttribute("imagenesCreadas",adminService.getCantidadDeImagenes());
        model.addAttribute("tagsCreados", adminService.getCantidadDeTags());
   

        return "admin/admin-dashboard";
    }

    @GetMapping("/showUsers")
    public String listUsers(Model model){
        model.addAttribute("users", userService.listUsers());
        return "admin/users-view";
    }

    @GetMapping("/manageImages")
    public String showUploadedImages(Model model){
        model.addAttribute("images", imageService.listImages());
        return "admin/manage-images";
    }

    @GetMapping("/manageTags")
    public String showTagsManagmentView(Model model){
        model.addAttribute("tags", imageService.listTags());
        model.addAttribute("newTag", new Tag());
        return "admin/manage-tags";
    }

    @PostMapping("/addTag")
    public String createTagView(Tag newTag){
    try{
        adminService.createTag(newTag);
    }
    catch(Exception e){
        System.out.println(e.getMessage());
    }
    return "redirect:/admin/manageTags";
    }

    @GetMapping("/deleteImage/{id}")
    public String deleteImage(@PathVariable("id") Integer id){
        try{
            adminService.deleteImage(id);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return "redirect:/admin/manageImages";
    }

    @GetMapping("/mostUsedTags")
    public String showMostUsedTags(Model model){
        model.addAttribute("tags", adminService.mostUsedTags(imageService.listTags()));
        return "admin/most-used-tags";
    }

}
