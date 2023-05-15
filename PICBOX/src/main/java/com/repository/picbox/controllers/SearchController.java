package com.repository.picbox.controllers;

import com.repository.picbox.model.Image;
import com.repository.picbox.model.Tag;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
import com.repository.picbox.services.ImageService;
import com.repository.picbox.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.text.html.Option;
import java.util.*;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    imageRepository imageRepository;

    @Autowired
    ImageService imageService;

    @Autowired
    tagRepository tagRepository;

    @PostMapping("/gallery/search")
    public String searchEngine(@RequestParam("tags")String tags, Model model){
        model.addAttribute("images", searchService.search(tags));
        model.addAttribute("tags", tags);
        return "landing-page";
    }

}
