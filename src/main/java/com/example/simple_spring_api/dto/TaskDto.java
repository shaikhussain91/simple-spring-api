package com.example.simple_spring_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskDto(
        Long id,

        // title cannot be null or empty
        @NotBlank(message = "Title cannot be blank") 
        @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters") 
        String title,

        // description can be null or empty, but if provided, must be between 0 and 500
        // characters
        @Size(max = 500, message = "Description must be between 0 and 500 characters") 
        String description,

        boolean completed) {

}
