package com.alkemy.disney.service;

import com.alkemy.disney.dto.GenreDTO;

import java.util.List;

public interface GenreService {
    GenreDTO save(GenreDTO genreDTO);
    List<GenreDTO> getAllGenres();
    GenreDTO update(Long id, GenreDTO genreDTO);
}
