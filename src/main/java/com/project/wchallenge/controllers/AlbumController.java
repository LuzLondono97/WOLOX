package com.project.wchallenge.controllers;

import com.project.wchallenge.models.Album;
import com.project.wchallenge.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/getAllAlbumsTemplate")
    private ResponseEntity<List<Album>> getAlbumsTemplate() {
        return ResponseEntity.ok(albumService.getAllAlbumsTemplate());
    }

    @GetMapping("getAlbumByIdTemplate/{id}")
    public ResponseEntity<Album> getAlbumByIdTemplate(@PathVariable(value = "id") Long albumId) {
        return ResponseEntity.ok(albumService.getAlbumByIdTemplate(albumId));
    }

    @GetMapping("getAlbumsByUserIdTemplate/{id}")
    public ResponseEntity<List<Album>> getAlbumsByUserIdTemplate(@PathVariable(value = "id") Long albumUserId) {
        return ResponseEntity.ok(albumService.getAlbumsByUserIdTemplate(albumUserId));
    }

}