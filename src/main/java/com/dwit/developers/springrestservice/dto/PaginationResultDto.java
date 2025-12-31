package com.dwit.developers.springrestservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PaginationResultDto {
    private List<ResultDto> items;
    private int pageNumber;
    private int pageSize;
    private int totalItems;
    private int totalPages;

}


