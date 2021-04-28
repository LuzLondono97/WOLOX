package com.project.wchallenge.repositories;

import com.project.wchallenge.models.AccessType;
import com.project.wchallenge.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u " +
            " FROM AlbumUser AS au, User AS u " +
            "WHERE au.userId = u.id " +
            "  AND au.accessType = :accessTypeId " +
            "  AND au.albumId = :albumId ")
    List<User> findByAlbumIdAndPermissions(@Param("albumId") Long albumId, @Param("accessTypeId") AccessType accessType);
}
