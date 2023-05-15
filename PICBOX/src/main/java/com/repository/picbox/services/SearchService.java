package com.repository.picbox.services;

import com.repository.picbox.model.Image;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class SearchService {

    @Autowired
    imageRepository imageRepository;

    @Autowired
    tagRepository tagRepository;

    public List<Image> search(String tags) {
        String[] tagsSplit = tags.split(" ");
        List<Image> allImages = imageRepository.findAll();
        List<Image> images = new ArrayList<>();
        searchEngine(allImages,images,tagsSplit);
        return images;
    }

    public void searchEngine(List<Image> allImages, List<Image> images, String[] tagsSplit){
        for (int i = 0; i < allImages.size(); i++) {
            for (int j = 0; j < tagsSplit.length; j++) {
                searchByTag(i,j,allImages,images,tagsSplit);
                searchByTitle(i,j,allImages,images,tagsSplit);
            }
        }
    }

    public void searchByTag(int i, int j, List<Image> allImages, List<Image> images, String[] tagsSplit){
        if (!images.contains(allImages.get(i))) {
        if (allImages.get(i).getTags().contains(tagRepository.findByName(tagsSplit[j]))) {
                images.add(allImages.get(i));
            }
        }

    }
    public void searchByTitle(int i, int j, List<Image> allImages, List<Image> images, String[] tagsSplit){
        if (!images.contains(allImages.get(i))) {
        if (allImages.get(i).getTitle().toLowerCase(Locale.ROOT).contains(tagsSplit[j].toLowerCase(Locale.ROOT))) {
                images.add(allImages.get(i));
            }
        }
    }
}
