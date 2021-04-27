package com.project.wchallenge.repositories;

import com.project.wchallenge.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}