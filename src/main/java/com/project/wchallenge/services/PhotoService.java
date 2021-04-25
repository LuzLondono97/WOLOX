package com.project.wchallenge.services;

import com.project.wchallenge.models.Photo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PhotoService {

    private static final RestTemplate restTemplate = new RestTemplate();

    public List<Photo> getPhotosTemplate() {
        ResponseEntity<Photo[]> response =
                restTemplate.getForEntity(
                        "https://jsonplaceholder.typicode.com/photos/",
                        Photo[].class);
        Photo[] photos = response.getBody();
        List<Photo> photosList = Arrays.asList(photos);
        return photosList;
    }

}
