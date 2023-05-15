package com.repository.picbox.controllers;

import com.repository.picbox.model.Image;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    imageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @PostMapping("/gallery/search")
    public String searchEngine(@RequestParam("tags")String tags, Model model){
        List<Image> images = imageRepository.findByTagsName(tags);
        model.addAttribute("images", images);
        model.addAttribute("tags", tags);
        return "landing-page";
    }

}
