package com.arty.musicservice.controller;

import com.arty.musicservice.record.GenreDTO;
import com.arty.musicservice.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Integer id) {
        return ResponseEntity.ok(genreService.findGenreById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.saveGenre(genreDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteGenre(@PathVariable Integer id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}