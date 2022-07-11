package com.alkemy.disney.service.impl;

import com.alkemy.disney.dto.MovieDTO;
import com.alkemy.disney.entity.CharacterEntity;
import com.alkemy.disney.entity.MovieEntity;
import com.alkemy.disney.exception.ParamNotFoundException;
import com.alkemy.disney.mapper.CharacterMapper;
import com.alkemy.disney.mapper.MovieMapper;
import com.alkemy.disney.repository.CharacterRepository;
import com.alkemy.disney.repository.MovieRepository;
import com.alkemy.disney.service.CharacterService;
import com.alkemy.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieMapper movieMapper;
    private MovieRepository movieRepository;
    private CharacterRepository characterRepository;

    @Autowired
    public MovieServiceImpl(MovieMapper movieMapper,
                            MovieRepository movieRepository,
                            CharacterRepository characterRepository) {

        this.movieMapper = movieMapper;
        this.movieRepository = movieRepository;
        this.characterRepository = characterRepository;
    }

    @Override
    public MovieDTO create(MovieDTO movieDTO) {

        MovieEntity movieEntity = movieMapper.movieDTO2Entity(movieDTO);
        MovieEntity savedMovie = movieRepository.save(movieEntity);

        return movieMapper.movieEntity2DTO(savedMovie, true);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> movieEntities = movieRepository.findAll();

        return movieMapper.movieEntityList2DTOList(movieEntities, true);
    }

    @Override
    public void delete(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public MovieDTO update(Long id, MovieDTO dto){

        Optional<MovieEntity> entity = movieRepository.findById(id);

        if (entity.isEmpty()) {
            throw new ParamNotFoundException("Error: Invalid movie id!!");
        }

        movieMapper.movieEntityRefreshValues(entity.get(), dto);

        MovieEntity entitySaved = movieRepository.save(entity.get());

        return movieMapper.movieEntity2DTO(entitySaved, true);
    }

    @Override
    public void addCharacter(Long idMovie, Long idCharacter) {

        CharacterEntity character = this.characterRepository.findById(idCharacter).get();
        MovieEntity movie = movieRepository.findById(idMovie).get();

        movie.addCharacter(character);

        this.movieRepository.save(movie);
    }

    @Override
    public void removeCharacter(Long idMovie, Long idCharacter) {

        CharacterEntity character = this.characterRepository.findById(idCharacter).get();
        MovieEntity movie = movieRepository.findById(idMovie).get();

        movie.removeCharacter(character);

        this.movieRepository.save(movie);
    }

}
