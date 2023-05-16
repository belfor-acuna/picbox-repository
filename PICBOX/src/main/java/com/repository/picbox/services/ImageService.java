package com.repository.picbox.services;

import com.repository.picbox.model.*;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
import com.repository.picbox.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImageService {

    @Autowired
    private imageRepository imageRepository;

    @Autowired
    private tagRepository tagRepository;

    @Autowired
    private userRepository userRepository;

    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    public Image getImageById(Integer id){
        return imageRepository.findById(id).get();
    }

    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    public Image uploadImage(ImageDTO imageDTO, byte[] file, Long id) throws IOException {
        Image newImage = new Image();
        newImage.setTitle(imageDTO.getTitle());
        newImage.setDescription(imageDTO.getDescription());
        Set<Tag> tagsList = new HashSet<>();
        tagsList.addAll(imageDTO.getTags());
        newImage.setTags(tagsList);
        newImage.setFile(file);
        User user = userRepository.getReferenceById(id);
        System.out.println("Dueño de esta imagen: "+user.getFullname());
        newImage.setUser(user);
        return imageRepository.save(newImage);

    }


    public byte[] imageByte(Integer id)  {
        return getImageById(id).getFile();
    }

    public User uploadUserImage(byte[] fileData, Long id) {
        User user = userRepository.getReferenceById(id);
        user.setProfilePicture(fileData);
        return userRepository.save(user);
    }
}

