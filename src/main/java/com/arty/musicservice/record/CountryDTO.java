package com.arty.musicservice.record;

import jakarta.validation.constraints.NotNull;

public record CountryDTO(
        @NotNull Integer countryId,
        @NotNull String countryName
) {}