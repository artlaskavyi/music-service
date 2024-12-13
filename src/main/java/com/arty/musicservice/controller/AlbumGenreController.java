package com.arty.musicservice.controller;

import com.arty.musicservice.record.AlbumGenreDTO;
import com.arty.musicservice.service.AlbumGenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albumgenres")
public class AlbumGenreController {
    private final AlbumGenreService albumGenreService;

    public AlbumGenreController(AlbumGenreService albumGenreService) {
        this.albumGenreService = albumGenreService;
    }

    @GetMapping
    public List<AlbumGenreDTO> getAllAlbumGenres() {
        return albumGenreService.findAllAlbumGenres();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumGenreDTO> createAlbumGenre(@RequestBody AlbumGenreDTO albumGenreDTO) {
        return ResponseEntity.ok(albumGenreService.saveAlbumGenre(albumGenreDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAlbumGenre(@PathVariable Integer id) {
        albumGenreService.deleteAlbumGenre(id);
        return ResponseEntity.noContent().build();
    }
}