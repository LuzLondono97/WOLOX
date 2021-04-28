package com.project.wchallenge.controllers;

import com.project.wchallenge.models.AlbumUser;
import com.project.wchallenge.services.AlbumUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/albums")
public class AlbumUserController {

    @Autowired
    private AlbumUserService albumUserService;

    @PostMapping("/addAlbumUser")
    public ResponseEntity<AlbumUser> addAlbumUser(@PathParam("albumId") Long albumId, @PathParam("userId") Long userId, @PathParam("accessTypeId") Long accessTypeId) throws Exception {
        try {
            return ResponseEntity.ok(albumUserService.addAlbumUser(albumId, userId, accessTypeId));
        } catch (Exception e) {
            throw new Exception("No tiene permisos para añadir el Album");
        }
    }

    @PutMapping("/updateAlbumPermissions")
    public ResponseEntity<AlbumUser> updateAlbumPermissions(@PathParam("albumId") Long albumId, @PathParam("userId") Long userId, @PathParam("accessTypeId") Long accessTypeId) throws Exception {
        try {
            return ResponseEntity.ok(albumUserService.updateAlbumPermissions(albumId, userId, accessTypeId));
        } catch (Exception e) {
            throw new Exception("No tiene permisos para añadir el Album");
        }
    }

}
