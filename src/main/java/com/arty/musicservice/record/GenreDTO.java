package com.arty.musicservice.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GenreDTO(
        @NotNull Integer genreId,
        @NotBlank String genreName,
        Integer parentGenreId
) {}