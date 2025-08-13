package com.example.simple_spring_api.dto;

import org.springframework.http.HttpStatus;

public record ResponseDto(
        HttpStatus status,
        String message,
        Object data) {

}
