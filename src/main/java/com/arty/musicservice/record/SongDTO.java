package com.arty.musicservice.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Time;

public record SongDTO(
        @NotNull Integer songId,
        @NotBlank String title,
        @NotNull Time songLength,
        String lyrics,
        @NotNull Integer albumId
) {}