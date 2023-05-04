package com.repository.picbox.repositories;

import com.repository.picbox.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface imageRepository extends JpaRepository<Image,Integer> {
}
