package com.repository.picbox.services;

import com.repository.picbox.model.*;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
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

    public List<Tag> listTags() {
        return tagRepository.findAll();
    }

    public Image getImageById(Integer id){
        return imageRepository.findById(id).get();
    }

    public List<Image> listImages() {
        return imageRepository.findAll();
    }

    public Image uploadImage(ImageDTO imageDTO, byte[] file) throws IOException {
        Image newImage = new Image();
        newImage.setTitle(imageDTO.getTitle());
        newImage.setDescription(imageDTO.getDescription());
        System.out.println(imageDTO.getTags().size());
        Set<Tag> tagsList = new HashSet<>();
        System.out.println("Creo nuevo set");
        tagsList.addAll(imageDTO.getTags());
        System.out.println("guardo los tags del tdo ne el set");
        newImage.setTags(tagsList);
        System.out.println("guardo el set en newimage");
        newImage.setFile(file);
        System.out.println("Guardó los bytes");
        System.out.println(file);
        System.out.println(newImage.getTitle());
        return imageRepository.save(newImage);

    }



    public byte[] imageByte(Integer id)  {
        return getImageById(id).getFile();
    }
}

