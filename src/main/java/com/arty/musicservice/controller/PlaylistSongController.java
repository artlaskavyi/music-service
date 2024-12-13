package com.arty.musicservice.controller;

import com.arty.musicservice.record.PlaylistSongDTO;
import com.arty.musicservice.service.PlaylistSongService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlistsongs")
public class PlaylistSongController {
    private final PlaylistSongService playlistSongService;

    public PlaylistSongController(PlaylistSongService playlistSongService) {
        this.playlistSongService = playlistSongService;
    }

    @GetMapping
    public List<PlaylistSongDTO> getAllPlaylistSongs() {
        return playlistSongService.findAllPlaylistSongs();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PlaylistSongDTO> createPlaylistSong(@RequestBody PlaylistSongDTO playlistSongDTO) {
        return ResponseEntity.ok(playlistSongService.savePlaylistSong(playlistSongDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePlaylistSong(@PathVariable Integer id) {
        playlistSongService.deletePlaylistSong(id);
        return ResponseEntity.noContent().build();
    }
}