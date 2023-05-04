package com.repository.picbox.repositories;

import com.repository.picbox.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface albumRepository extends JpaRepository<Album,Integer> {
    Optional<Album> findAlbumById(Integer id);
}
