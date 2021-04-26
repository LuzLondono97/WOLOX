package com.project.wchallenge.services;

import com.project.wchallenge.models.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class PhotoService {

    private static final RestTemplate restTemplate = new RestTemplate();
    private final AlbumService albumService;
    String urlPhoto = "https://jsonplaceholder.typicode.com/photos";
    String urlPhotoByAlbumId = urlPhoto + "?albumId=";

    @Autowired
    public PhotoService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public List<Photo> getAllPhotosTemplate() {
        ResponseEntity<Photo[]> response =
                restTemplate.getForEntity(
                        urlPhoto,
                        Photo[].class);
        Photo[] photos = response.getBody();
        List<Photo> photosList = Arrays.asList(photos);
        return photosList;
    }

    public Photo getPhotoByIdTemplate(Long photoId) {
        ResponseEntity<Photo> response = restTemplate.exchange(
                urlPhoto + "/" + photoId.toString(),
                HttpMethod.GET, null,
                new ParameterizedTypeReference<Photo>() {
                }
        );
        return response.getBody();
    }

    public List<Photo> getPhotosByUserIdTemplate(Long photoAlbumId) {
        List<Photo> photos = new ArrayList<>();
        albumService.getAlbumsByUserIdTemplate(photoAlbumId).forEach(albumId -> {
            photos.addAll(
                    Objects.requireNonNull(restTemplate.exchange(
                            (urlPhotoByAlbumId + albumId.getId()),
                            HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<Photo>>() {
                            }
                    ).getBody())
            );
        });
        return photos;
    }

}
