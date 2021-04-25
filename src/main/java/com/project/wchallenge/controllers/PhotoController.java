package com.project.wchallenge.controllers;

import com.project.wchallenge.models.Photo;
import com.project.wchallenge.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping("/getPhotosTemplate")
    private ResponseEntity<List<Photo>> getPhotosTemplate() {
        return ResponseEntity.ok(photoService.getPhotosTemplate());
    }

}
