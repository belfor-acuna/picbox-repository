package com.repository.picbox.repositories;

import com.repository.picbox.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface imageRepository extends JpaRepository<Image,Integer> {

    List<Image> findByTagsName(String name);

}
