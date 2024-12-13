package com.arty.musicservice.record;

import jakarta.validation.constraints.NotNull;

public record AlbumGenreDTO(
        @NotNull Integer albumGenreId,
        @NotNull Integer albumId,
        @NotNull Integer genreId
) {}
