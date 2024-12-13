package com.arty.musicservice.controller;

import com.arty.musicservice.record.AlbumDTO;
import com.arty.musicservice.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumDTO> getAllAlbums() {
        return albumService.findAllAlbums();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Integer id) {
        return ResponseEntity.ok(albumService.findAlbumById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.saveAlbum(albumDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}