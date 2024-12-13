package com.arty.musicservice.controller;

import com.arty.musicservice.record.SongDTO;
import com.arty.musicservice.service.SongService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<SongDTO> getAllSongs() {
        return songService.findAllSongs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongDTO> getSongById(@PathVariable Integer id) {
        return ResponseEntity.ok(songService.findSongById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SongDTO> createSong(@RequestBody SongDTO songDTO) {
        return ResponseEntity.ok(songService.saveSong(songDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSong(@PathVariable Integer id) {
        songService.deleteSong(id);
        return ResponseEntity.noContent().build();
    }
}