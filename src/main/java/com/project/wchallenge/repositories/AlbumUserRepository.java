package com.project.wchallenge.repositories;

import com.project.wchallenge.models.AlbumUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumUserRepository extends JpaRepository<AlbumUser, Long> {

    Boolean existsAlbumUserByAlbum_IdAndUser_Id(Long albumId, Long userId);

}