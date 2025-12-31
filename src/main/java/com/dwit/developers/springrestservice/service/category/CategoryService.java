package com.dwit.developers.springrestservice.service.category;

import com.dwit.developers.springrestservice.dto.CategoryDto;
import com.dwit.developers.springrestservice.service.GenericService;

import java.util.List;

public interface CategoryService extends GenericService<CategoryDto,Integer> {
    public List<CategoryDto> findCategoriesByStationeryType(Integer stationeryTypeId);
}
