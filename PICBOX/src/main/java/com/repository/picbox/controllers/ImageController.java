package com.repository.picbox.controllers;
import com.repository.picbox.model.*;
import com.repository.picbox.services.ImageService;
import com.repository.picbox.services.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/gallery")
    public String gallery(Model model) {
        model.addAttribute("images", imageService.listImages());
        return "landing-page";
    }



    @GetMapping("/upload/{id}")
    private String showUploadImageView(Model model, @PathVariable("id") Long id){
        model.addAttribute("currentUser", userService.getUserById(id));
        model.addAttribute("imageDTO", new ImageDTO());
        model.addAttribute("tags",imageService.listTags());
        return ("/logged-user/upload-image");
    }

    @PostMapping("/api/upload/{id}")
    public String uploadSubmit(ImageDTO imageDTO, Model model, @RequestParam("file")MultipartFile file,@PathVariable("id")Long id){
        try{
            byte[] fileData = file.getBytes();
            imageService.uploadImage(imageDTO, fileData,id);
        }
        catch (Exception e){
            System.err.println("ERROR postmapping: "+e);
            return "redirect:/upload/"+id;

        }
        return "redirect:/userGallery/"+id;
    }

    @PostMapping("/api/uploadUserImage/{id}")
    public String uploadUserImage(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id){
        try{
            byte[] fileData = file.getBytes();
            imageService.uploadUserImage(fileData,id);
        } catch (Exception e){
            System.err.println("Error al subir imagen de usuario: "+e);
            return "redirect:/userSettings/general/"+id;
        }
        return "redirect:/userSettings/general/"+id;
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

    @GetMapping("/userImage/{id}")
    public ResponseEntity<byte[]> showUserImage(@PathVariable("id") Long id, Model model) throws IOException {
        User user = userService.getUserById(id);
        if (user.getProfilePicture() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(user.getProfilePicture().length);
        return new ResponseEntity<>(user.getProfilePicture(), headers, HttpStatus.OK);
    }

     @GetMapping("/fullview/image/{id}")
     public String showFullViewImage(Model model, @PathVariable("id") Integer id) throws IOException {
        model.addAttribute("image",imageService.getImageById(id));
        return "full-view-img";
     }







}
