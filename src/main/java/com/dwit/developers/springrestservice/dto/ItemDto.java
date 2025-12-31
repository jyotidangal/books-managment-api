package com.dwit.developers.springrestservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Integer id;
    @NotBlank(message = "Item name cannot be blank")
    private String name;

    @NotNull(message = "Category ID is required")
    @Positive(message = "Category ID must be a positive number")
    private Integer categoryId;

    @NotNull(message = "Rack ID is required")
    @Positive(message = "Rack ID must be a positive number")
    private Integer rackId;
}
