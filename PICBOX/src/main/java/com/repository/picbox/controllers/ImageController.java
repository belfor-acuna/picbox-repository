package com.repository.picbox.controllers;
import com.repository.picbox.model.*;
import com.repository.picbox.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ImageController {


    @Autowired
    private ImageService imageService;



    @GetMapping("/gallery")
    public String gallery(Model model) {
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
    public String uploadSubmit(ImageDTO imageDTO, Model model, @RequestParam("file")MultipartFile file){
        try{
            byte[] fileData = file.getBytes();
            imageService.uploadImage(imageDTO, fileData);
        }
        catch (Exception e){
            System.err.println("ERROR postmapping: "+e);
            return "redirect:/upload";

        }
        return "redirect:/gallery";
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> showImage(@PathVariable("id") Integer id, Model model) throws IOException {
            if (imageService.imageByte(id) == null) {
                return ResponseEntity.notFound().build();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(imageService.imageByte(id).length);
            return new ResponseEntity<>(imageService.imageByte(id), headers, HttpStatus.OK);
    }

     @GetMapping("/fullview/image/{id}")
     public String showFullViewImage(Model model, @PathVariable("id") Integer id) throws IOException {
        model.addAttribute("image",imageService.getImageById(id));
        return "full-view-img";
     }




}
