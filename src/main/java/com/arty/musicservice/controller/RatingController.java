package com.arty.musicservice.controller;

import com.arty.musicservice.record.RatingDTO;
import com.arty.musicservice.service.RatingService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public List<RatingDTO> getAllRatings() {
        return ratingService.findAllRatings();
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.saveRating(ratingDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable Integer id, @RequestBody RatingDTO ratingDTO) {
        return ResponseEntity.ok(ratingService.updateRating(id, ratingDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteRating(@PathVariable Integer id) {
        ratingService.deleteRating(id);
        return ResponseEntity.noContent().build();
    }
}