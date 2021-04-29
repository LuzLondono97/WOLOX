package com.project.wchallenge.service;

import com.project.wchallenge.models.AccessType;
import com.project.wchallenge.models.AlbumUser;
import com.project.wchallenge.models.User;
import com.project.wchallenge.repositories.AlbumUserRepository;
import com.project.wchallenge.repositories.UserRepository;
import com.project.wchallenge.services.AlbumUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AlbumServiceTest {

    private List<User> userList = new ArrayList<>();
    private AlbumUser albumUser;

    @Mock
    private UserRepository userRepository;
    @Mock
    private AlbumUserRepository albumUserRepository;

    @InjectMocks
    private AlbumUserService albumUserService;


    public AlbumServiceTest(AlbumUser albumUser) {
        super();
        this.albumUser = albumUser;
    }


    @Test
    void getUsersPermissionsAlbum() throws Exception {
        when(userRepository.findByAlbumIdAndPermissions(Mockito.anyLong(), Mockito.any(AccessType.class))).thenReturn(userList);
        List<User> userList = albumUserService.getUsersPermissionsAlbum(5L, 3L);
        Boolean result = false;
        if (null != userList && !"".equals(userList)) {
            result = true;
        }
        Assertions.assertTrue(result, "userList: La lista tiene informacion");
    }

    @Test
    void updateAccessToAlbum() throws Exception {
        when(albumUserRepository.findByAlbumIdAndUserId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(Optional.of(albumUser));
        when(albumUserRepository.save(any(AlbumUser.class))).thenReturn(albumUser);

        AlbumUser albumUser = albumUserService.updateAlbumPermissions(3L, 1L, 2L);
        Boolean result = false;
        if (null != albumUser && !"".equals(albumUser)) {
            result = true;
        }
        Assertions.assertTrue(result, "albumUser: La lista tiene informacion");
    }

}
