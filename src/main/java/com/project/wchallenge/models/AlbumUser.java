package com.project.wchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "album_user")
public class AlbumUser implements Serializable {

    private static final long serialVersionUID = 8799656478674716637L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(nullable = false)
    private Album album;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @Column(nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private AccessType accessType;
}
