package com.arty.musicservice.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArtistDTO(
        @NotNull Integer artistId,
        @NotBlank String artistName,
        Integer yearOfCreation,
        Integer countryId
) {}