package com.dwit.developers.springrestservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    @NotBlank(message = "Category name cannot be blank")
    private String name;

    @NotNull(message = "Stationery Type ID is required")
    @Positive(message = "Stationery Type ID must be a positive number")
    private Integer stationeryTypeId;

}
