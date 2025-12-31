package com.dwit.developers.springrestservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationeryTypeDto {
    private Integer id;
    @NotBlank(message = "Name can't be blank")
    private String name;


}
