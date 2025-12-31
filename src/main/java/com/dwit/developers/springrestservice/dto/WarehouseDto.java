package com.dwit.developers.springrestservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDto {

    private Integer id;

    @NotNull(message = "name can't be null")
    @NotBlank(message = "Name can't be blank")
    private String name;

    @NotNull(message = "location can't be null")
    @NotBlank(message = "Location cannot be blank")
    private String location;
}
