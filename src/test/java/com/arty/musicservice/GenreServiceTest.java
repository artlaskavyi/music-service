package com.arty.musicservice;

import com.arty.musicservice.exception.EntityNotFoundException;
import com.arty.musicservice.exception.FieldNotUniqueException;
import com.arty.musicservice.model.Genre;
import com.arty.musicservice.record.GenreDTO;
import com.arty.musicservice.repository.GenreRepository;
import com.arty.musicservice.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@Profile("!prod")
@ExtendWith(MockitoExtension.class)
public class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @InjectMocks
    private GenreService genreService;

    private Genre genre;
    private GenreDTO genreDTO;
    private Genre parentGenre;

    @BeforeEach
    public void setUp() {
        parentGenre = new Genre();
        parentGenre.setGenreId(1);
        parentGenre.setGenreName("Rock");

        genre = new Genre();
        genre.setGenreId(2);
        genre.setGenreName("Hard Rock");
        genre.setParentGenre(parentGenre);

        genreDTO = new GenreDTO(2, "Hard Rock", 1);
    }

    @Test
    public void testSaveGenre_Success() {
        when(genreRepository.findByGenreName("Hard Rock")).thenReturn(null);
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);

        GenreDTO savedGenreDTO = genreService.saveGenre(genreDTO);

        assertEquals(genreDTO, savedGenreDTO);
    }

    @Test
    public void testSaveGenre_GenreNameNotUnique() {
        when(genreRepository.findByGenreName("Hard Rock")).thenReturn(genre);

        assertThrows(FieldNotUniqueException.class, () -> genreService.saveGenre(genreDTO));
    }

    @Test
    public void testFindGenreById_Success() {
        when(genreRepository.findById(2)).thenReturn(Optional.of(genre));

        GenreDTO foundGenreDTO = genreService.findGenreById(2);

        assertEquals(genreDTO, foundGenreDTO);
    }

    @Test
    public void testFindGenreById_NotFound() {
        when(genreRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> genreService.findGenreById(2));
    }

    @Test
    public void testFindAllGenres() {
        when(genreRepository.findAll()).thenReturn(List.of(genre));

        List<GenreDTO> genres = genreService.findAllGenres();

        assertEquals(1, genres.size());
        assertEquals(genreDTO, genres.get(0));
    }

    @Test
    public void testDeleteGenre_Success() {
        when(genreRepository.existsById(2)).thenReturn(true);
        doNothing().when(genreRepository).deleteById(2);

        assertDoesNotThrow(() -> genreService.deleteGenre(2));
    }

    @Test
    public void testDeleteGenre_NotFound() {
        when(genreRepository.existsById(2)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> genreService.deleteGenre(2));
    }

    @Test
    public void testFindByGenreName_Success() {
        when(genreRepository.findByGenreName("Hard Rock")).thenReturn(genre);

        GenreDTO foundGenreDTO = genreService.findByGenreName("Hard Rock");

        assertEquals(genreDTO, foundGenreDTO);
    }

    @Test
    public void testFindByGenreName_NotFound() {
        when(genreRepository.findByGenreName("Hard Rock")).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> genreService.findByGenreName("Hard Rock"));
    }
}