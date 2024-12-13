package com.arty.musicservice.controller;

import com.arty.musicservice.record.ArtistDTO;
import com.arty.musicservice.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<ArtistDTO> getAllArtists() {
        return artistService.findAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Integer id) {
        return ResponseEntity.ok(artistService.findArtistById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        return ResponseEntity.ok(artistService.saveArtist(artistDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteArtist(@PathVariable Integer id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}