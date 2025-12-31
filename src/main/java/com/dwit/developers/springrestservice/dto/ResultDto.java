package com.dwit.developers.springrestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDto {

    private Integer id;
    private String name;
    private Integer categoryId;
    private Integer rackId;
    private String rackName;
    private String roomName;
}
