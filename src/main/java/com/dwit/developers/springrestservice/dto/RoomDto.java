package com.dwit.developers.springrestservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RoomDto {
    private Integer id;

    @NotBlank(message = "Room name cannot be blank")
    private String name;

    @NotNull(message = "Key number is required")
    @Positive(message = "Key number must be a positive number")
    private Integer keyNumber;

    @NotNull(message = "Warehouse ID is required")
    @Positive(message = "Warehouse ID must be a positive number")
    private Integer warehouseId;

}
