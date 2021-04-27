package com.project.wchallenge.controllers;

import com.project.wchallenge.models.AlbumUser;
import com.project.wchallenge.repositories.AlbumRepository;
import com.project.wchallenge.repositories.AlbumUserRepository;
import com.project.wchallenge.repositories.UserRepository;
import com.project.wchallenge.services.AlbumService;
import com.project.wchallenge.services.AlbumUserService;
import com.project.wchallenge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/albums")
public class AlbumUserController {

    @Autowired
    private AlbumUserService albumUserService;

    private final UserService userService;
    private final AlbumService albumService;

    @Autowired
    public AlbumUserController(UserService userService, AlbumService albumService) {
        this.userService = userService;
        this.albumService = albumService;
    }

    @PostMapping("/addAlbumUser")
    public ResponseEntity<AlbumUser> shareAlbumWithUser(@PathParam("albumId") Long albumId, @PathParam("userId") Long userId, @PathParam("accessTypeId") Long accessTypeId) throws Exception {
        try {
            return ResponseEntity.ok(albumUserService.addAlbumUser(albumId, userId, accessTypeId));
        } catch (Exception e) {
            throw new Exception("No tiene permisos para añadir el Album");
        }
    }

}
