package com.project.wchallenge.services;

import com.project.wchallenge.models.Album;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AlbumService {

    private static final RestTemplate restTemplate = new RestTemplate();
    String urlAlbum = "https://jsonplaceholder.typicode.com/albums";
    String urlAlbumByUserId = urlAlbum + "?userId=";

    public List<Album> getAllAlbumsTemplate() {
        ResponseEntity<Album[]> response =
                restTemplate.getForEntity(
                        urlAlbum,
                        Album[].class
                );
        Album[] albums = response.getBody();
        List<Album> albumsList = Arrays.asList(albums);
        return albumsList;
    }

    public Album getAlbumByIdTemplate(Long albumId) {
        ResponseEntity<Album> response =
                restTemplate.exchange(
                        urlAlbum + "/" + albumId.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<Album>() {
                        }
                );
        return response.getBody();
    }

    public List<Album> getAlbumsByUserIdTemplate(Long albumUserId) {
        ResponseEntity<List<Album>> response =
                restTemplate.exchange(
                        urlAlbumByUserId + albumUserId.toString(),
                        HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Album>>() {
                        }
                );
        return response.getBody();
    }

}
