package com.arty.musicservice.record;

import jakarta.validation.constraints.NotNull;

public record PlaylistSongDTO(
        @NotNull Integer playlistSongId,
        @NotNull Integer playlistId,
        @NotNull Integer songId
) {}