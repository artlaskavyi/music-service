package com.arty.musicservice.controller;

import com.arty.musicservice.record.PlaylistDTO;
import com.arty.musicservice.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public List<PlaylistDTO> getAllPlaylists() {
        return playlistService.findAllPlaylists();
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        return ResponseEntity.ok(playlistService.savePlaylist(playlistDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@PathVariable Integer id, @RequestBody PlaylistDTO playlistDTO) {
        return ResponseEntity.ok(playlistService.updatePlaylist(id, playlistDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Integer id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }
}