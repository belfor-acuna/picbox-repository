package com.repository.picbox.controllers;

import com.repository.picbox.model.Image;
import com.repository.picbox.model.ImageDTO;

import com.repository.picbox.model.Tag;
import com.repository.picbox.model.TagDTO;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
import com.repository.picbox.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ImageController {


    @Autowired
    private ImageService imageService;

    @Autowired
    private com.repository.picbox.repositories.imageRepository imageRepository;

    @Autowired
    private com.repository.picbox.repositories.tagRepository tagRepository;

    @GetMapping("/gallery")
    public String gallery(Model model){
        model.addAttribute("images", imageService.listImages());
        return "landing-page";
    }


    @GetMapping("/upload")
    private String showUploadImageView(Model model){
        model.addAttribute("imageDTO", new ImageDTO());
        model.addAttribute("tags",imageService.listTags());
        return ("upload-image");
    }

    @PostMapping("/api/upload")
    public String uploadSubmit(@ModelAttribute Image image,ImageDTO imageDTO,@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "redirect:/api/upload";
        }
        image.setTitle(imageDTO.getTitle());
        image.setDescription(imageDTO.getDescription());
        image.setImage(file.getBytes());
        imageRepository.save(image);
        return "redirect:/image/" + image.getId();
    }

    @GetMapping("/image/{id}")
    public String viewImage(@PathVariable Integer id, Model model) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            model.addAttribute("image", image.get());
        } else {
            return "redirect:/";
        }
        return "redirect:/gallery";
    }

    @PostMapping("/image/{id}/tag")
    public String assignTag(@PathVariable Integer id, @RequestParam("tagIds") Set<Integer> tagIds) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            Set<Tag> tags = (Set<Tag>) tagRepository.findAllById(tagIds);
            image.get().getTags().addAll(tags);
            imageRepository.save(image.get());

        }
        return "redirect:/gallery";
    }


}
