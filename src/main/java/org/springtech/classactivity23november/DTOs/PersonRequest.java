package org.springtech.classactivity23november.DTOs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PersonRequest(
        String name,
        @NotNull(message = "email is a required field")
        @Email(message = "ensure the email address is valid.")
        String email) {
}
