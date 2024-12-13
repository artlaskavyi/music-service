package com.arty.musicservice.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlaylistDTO(
        @NotNull Integer playlistId,
        @NotBlank String title,
        String description,
        @NotNull Integer userId
) {}