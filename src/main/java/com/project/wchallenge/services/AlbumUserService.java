package com.project.wchallenge.services;

import com.project.wchallenge.models.AccessType;
import com.project.wchallenge.models.Album;
import com.project.wchallenge.models.AlbumUser;
import com.project.wchallenge.models.User;
import com.project.wchallenge.repositories.AlbumRepository;
import com.project.wchallenge.repositories.AlbumUserRepository;
import com.project.wchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlbumUserService {

    private final AlbumUserRepository albumUserRepository;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumUserService(AlbumUserRepository albumUserRepository, UserRepository userRepository, AlbumRepository albumRepository) {
        this.albumUserRepository = albumUserRepository;
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
    }

    private Boolean existsAlbumUser(Long albumId, Long userId) {
        Optional<AlbumUser> albumUser = albumUserRepository.findByAlbumIdAndUserId(albumId, userId);
        Boolean result = false;
        if (albumUser.isPresent()) {
            result = true;
        }
        return result;
    }

    public AlbumUser addAlbumUser(Long albumId, Long userId, Long accessTypeId) throws Exception {
        if (null == albumId || null == userId || null == accessTypeId) {
            throw new Exception("El album, usuario y permiso no pueden ser nulo");
        }

        AccessType accessType = AccessType.getByIdAccess(accessTypeId);
        if (accessType.equals(AccessType.NO_ACCESS)) {
            throw new Exception("accessType " + accessType);
        }

        Optional<User> user = userRepository.findById(userId);
        Optional<Album> album = albumRepository.findById(albumId);
        if (user.isPresent() && album.isPresent()) {
            if (!this.existsAlbumUser(albumId, userId)) {
                AlbumUser albumUser = AlbumUser.builder()
                        .userId(userId)
                        .albumId(albumId)
                        .accessType(accessType)
                        .build();
                return albumUserRepository.save(albumUser);
            } else {
                throw new Exception("Ya existe un registro para este album y usuario");
            }
        } else {
            throw new Exception("El usuario o album no existe");
        }
    }

    public AlbumUser updateAlbumPermissions(Long albumId, Long userId, Long accessTypeId) throws Exception {
        if (null == albumId || null == userId || null == accessTypeId) {
            throw new Exception("El album, usuario y permiso no pueden ser nulo");
        }

        AccessType accessType = AccessType.getByIdAccess(accessTypeId);
        if (accessType.equals(AccessType.NO_ACCESS)) {
            throw new Exception("accessType " + accessType);
        }

        Optional<User> user = userRepository.findById(userId);
        Optional<Album> album = albumRepository.findById(albumId);
        if (user.isPresent() && album.isPresent()) {
            if (this.existsAlbumUser(albumId, userId)) {
                AlbumUser albumUser = AlbumUser.builder()
                        .userId(userId)
                        .albumId(albumId)
                        .accessType(accessType)
                        .build();
                return albumUserRepository.save(albumUser);
            } else {
                throw new Exception("No existe un registro para este album y usuario");
            }
        } else {
            throw new Exception("El usuario o album no existe");
        }
    }

    public List<User> getUsersPermissionsAlbum(Long albumId, Long accessTypeId) throws Exception {
        AccessType accessType = AccessType.getByIdAccess(accessTypeId);
        if (accessType.equals(AccessType.NO_ACCESS)) {
            throw new Exception("accessType " + accessType);
        }

        List<User> user = userRepository.findByAlbumIdAndPermissions(albumId, accessType);
        if (null != user || !"".equals(user)) {
            return user.stream().collect(Collectors.toList());
        } else {
            throw new Exception("No existe un usuario con este album y estos permisos");
        }
    }

}
