package com.repository.picbox.services;

import com.repository.picbox.model.*;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private imageRepository imageRepository;

    @Autowired
    private tagRepository tagRepository;

    public List<Tag> listTags() {
        return tagRepository.findAll();
    }



    public List<Image> listImages() {
        return imageRepository.findAll();
    }

}

