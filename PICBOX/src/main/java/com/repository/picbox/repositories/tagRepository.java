package com.repository.picbox.repositories;

import com.repository.picbox.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface tagRepository extends JpaRepository<Tag,Integer> {
    Tag findByName(String tagName);

}
