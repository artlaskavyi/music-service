package com.arty.musicservice.service;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.exception.FieldNotUniqueException;
import com.arty.musicservice.model.Genre;
import com.arty.musicservice.record.GenreDTO;
import com.arty.musicservice.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public GenreDTO saveGenre(GenreDTO genreDTO) {
        if (genreRepository.findByGenreName(genreDTO.genreName()) != null) {
            throw new FieldNotUniqueException("Genre Name", genreDTO.genreName());
        }
        Genre genre = new Genre();
        genre.setGenreName(genreDTO.genreName());

        genreRepository.save(genre);
        return genreDTO;
    }

    public GenreDTO findGenreById(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre", id));
        if (genre.getParentGenre() == null){
            return new GenreDTO(genre.getGenreId(), genre.getGenreName(), null);
        }else{
            return new GenreDTO(genre.getGenreId(), genre.getGenreName(), genre.getParentGenre().getGenreId());
        }

    }

    public List<GenreDTO> findAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        return genres.stream()
                .map(genre -> {
                    if (genre.getParentGenre() == null) {
                        return new GenreDTO(genre.getGenreId(), genre.getGenreName(), null);
                    } else {
                        return new GenreDTO(genre.getGenreId(), genre.getGenreName(), genre.getParentGenre().getGenreId());
                    }
                })
                .toList();
    }

    public void deleteGenre(Integer id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Genre", id);
        }
    }

    public GenreDTO findByGenreName(String genreName) {
        Genre genre = genreRepository.findByGenreName(genreName);
        if (genre == null) {
            throw new EntityNotFoundException("Genre with name", genreName);
        }

        if (genre.getParentGenre() == null) {
            return new GenreDTO(genre.getGenreId(), genre.getGenreName(), null);
        } else {
            return new GenreDTO(genre.getGenreId(), genre.getGenreName(), genre.getParentGenre().getGenreId());
        }
    }
}