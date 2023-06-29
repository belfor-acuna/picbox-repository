package com.repository.picbox.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repository.picbox.model.Image;
import com.repository.picbox.model.Tag;
import com.repository.picbox.model.User;
import com.repository.picbox.repositories.imageRepository;
import com.repository.picbox.repositories.tagRepository;
import com.repository.picbox.repositories.userRepository;

@Service
public class AdminService {

@Autowired
userRepository userRepository;

@Autowired
imageRepository imageRepository;

@Autowired
tagRepository tagRepository;

 public User getUserById(Long id){
        return userRepository.findById(id).get();
    }

public Image getImageById(Integer id){
    return imageRepository.findById(id).get();
}

public int getCantidadDeUsuarios(){
    List<User> usersInApp = userRepository.findAll();
    return usersInApp.size();
}

 public void deleteUser(Long id){
        userRepository.delete(getUserById(id));
    }

public int getCantidadDeImagenes(){
    List<Image> imagesInApp = imageRepository.findAll();
    return imagesInApp.size();
}

public Object getCantidadDeTags() {
    List<Tag> tagsInApp = tagRepository.findAll();
    return tagsInApp.size();
}

public void createTag(Tag tag) throws Exception{
    Optional<Tag> tagFound = tagRepository.findTagByName(tag.getName());
    if(tagFound.isPresent())throw new Exception ("Ya existe este tag");
    else{
        tagRepository.save(tag);
    }
}

public void deleteImage(Integer id){
    imageRepository.delete(getImageById(id));
}

}
