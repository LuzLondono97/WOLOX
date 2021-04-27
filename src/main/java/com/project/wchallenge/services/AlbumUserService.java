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

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
        return albumUserRepository.existsAlbumUserByAlbum_IdAndUser_Id(albumId, userId);
    }

    private User userVerified(User user) {
        Optional<User> userVerified = userRepository.findById(user.getId());
        return userVerified.orElseGet(() -> userRepository.save(user));
    }

    private Album albumVerified(Album album) {
        Optional<Album> albumVerified = albumRepository.findById(album.getId());
        return albumVerified.orElseGet(() -> albumRepository.save(album));
    }

    public AlbumUser addAlbumUser(Long albumId, Long userId, Long accessTypeId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        Optional<Album> album = albumRepository.findById(albumId);

        AccessType accessType = AccessType.getByIdAccess(accessTypeId);
        if (accessType.equals(AccessType.UNKNOWN)) {
            throw new Exception("idAccessType " + accessType);
        }

        if (user.isPresent() && album.isPresent()) {
            Long idAlbum = album.get().getId();
            Long idUser = user.get().getId();
            Long idAlbumUser = album.get().getUserId();

            if (!this.existsAlbumUser(idAlbum, idUser) && !idAlbumUser.equals(idUser)) {
                User userEntity = userVerified(user.get());
                Album albumEntity = albumVerified(album.get());

                AlbumUser albumUser = AlbumUser.builder().user(userEntity).album(albumEntity).accessType(accessType).build();
                return albumUserRepository.save(albumUser);
            }
        }

        return null;
    }

}
