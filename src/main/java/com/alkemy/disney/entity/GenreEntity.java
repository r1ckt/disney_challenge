package com.alkemy.disney.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "genre")
@Getter
@Setter
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id; // can be Integer

    @Column(name = "genre_name")
    private String name;

    @Column(name = "genre_image")
    private String image;

}
