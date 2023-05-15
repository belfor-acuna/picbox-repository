package com.repository.picbox.controllers;

import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
import com.repository.picbox.services.ImageService;
import com.repository.picbox.services.SearchService;
import com.repository.picbox.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    UserService userService;

    @PostMapping("/gallery/search")
    public String searchEngine(@RequestParam("tags")String tags, Model model){
        model.addAttribute("images", searchService.search(tags));
        model.addAttribute("tags", tags);
        return "landing-page";
    }

    @GetMapping("/gallery/search/{tags}")
    public String searchEngineByClickingTag(@PathVariable("tags") String tags, Model model){
        model.addAttribute("images", searchService.search(tags));
        model.addAttribute("tags", tags);
        return "landing-page";
    }

    @PostMapping("/loggedGallery/search")
    public String LoggedInSearchEngine(@RequestParam("writtentags")String tags, Model model, @RequestParam("userid")Long id){
        model.addAttribute("currentUser",userService.getUserById(id));
        model.addAttribute("images", searchService.search(tags));
        model.addAttribute("writtentags", tags);
        return "/logged-user/logged-in-gallery";
    }

    @GetMapping("/loggedGallery/{id}/search/{tags}")
    public String LoggedInSearchEngineByClickingTag(@PathVariable("tags") String tags, Model model, @PathVariable("id") Long id){
        model.addAttribute("currentUser",userService.getUserById(id));
        model.addAttribute("images", searchService.search(tags));
        model.addAttribute("tags", tags);
        return "/logged-user/logged-in-gallery";
    }

}
