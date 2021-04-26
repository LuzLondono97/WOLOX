package com.project.wchallenge.controllers;

import com.project.wchallenge.models.Photo;
import com.project.wchallenge.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/getAllPhotosTemplate")
    private ResponseEntity<List<Photo>> getPhotosTemplate() {
        return ResponseEntity.ok(photoService.getAllPhotosTemplate());
    }

    @GetMapping("getPhotoByIdTemplate/{id}")
    public ResponseEntity<Photo> getPhotoByIdTemplate(@PathVariable(value = "id") Long photoId) {
        return ResponseEntity.ok(photoService.getPhotoByIdTemplate(photoId));
    }

    @GetMapping("getPhotosByUserIdTemplate/{id}")
    public ResponseEntity<List<Photo>> getPhotosByUserIdTemplate(@PathVariable(value = "id") Long photoAlbumId) {
        return ResponseEntity.ok(photoService.getPhotosByUserIdTemplate(photoAlbumId));
    }

}
