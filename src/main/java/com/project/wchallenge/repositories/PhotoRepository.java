package com.project.wchallenge.repositories;

import com.project.wchallenge.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
