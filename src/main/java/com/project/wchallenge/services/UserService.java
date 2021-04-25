package com.project.wchallenge.services;

import com.project.wchallenge.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    private static final RestTemplate restTemplate = new RestTemplate();

    public List<User> getUsersTemplate() {
        ResponseEntity<User[]> response =
                restTemplate.getForEntity(
                        "https://jsonplaceholder.typicode.com/users/",
                        User[].class);
        User[] users = response.getBody();
        List<User> usersList = Arrays.asList(users);
        return usersList;
    }

}
