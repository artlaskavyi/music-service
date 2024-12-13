package com.arty.musicservice.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        //@NotNull Integer userId,
        @NotNull String username,
        @NotNull @Email String email,
        @NotNull String userPassword
) {}
