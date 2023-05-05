package com.repository.picbox.repositories;

import com.repository.picbox.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface tagRepository extends JpaRepository<Tag,Integer> {
    Optional<Tag> findByName(String tagName);

}