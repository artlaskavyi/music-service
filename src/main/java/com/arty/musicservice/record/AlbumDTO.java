package com.arty.musicservice.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlbumDTO(
        @NotNull Integer albumId,
        @NotBlank String title,
        Integer releaseYear,
        @NotNull Integer artistId // Foreign Key to Artist
) {}
