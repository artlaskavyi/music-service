package com.arty.musicservice.record;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RatingDTO(
        @NotNull Integer ratingId,
        @NotNull @Min(1) @Max(5) Integer ratingValue,
        String reviewText,
        @NotNull Integer userId,
        @NotNull Integer songId
) {}