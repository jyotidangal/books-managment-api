package com.dwit.developers.springrestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RackDto {
    private Integer id;
    @NotBlank(message = "Rack name cannot be blank")
    private String name;

    @NotNull(message = "Number of brackets is required")
    @Positive(message = "Number of brackets must be greater than zero")
    private Integer numberOfBrackets;

    @NotNull(message = "Length is required")
    @Positive(message = "Length must be greater than zero")
    private Float length;

    @NotNull(message = "Breadth is required")
    @Positive(message = "Breadth must be greater than zero")
    private Float breadth;

    @NotNull(message = "Room ID is required")
    @Positive(message = "Room ID must be a positive number")
    private Integer roomId;
}
