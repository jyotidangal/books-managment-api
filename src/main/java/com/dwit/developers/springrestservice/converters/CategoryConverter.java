package com.dwit.developers.springrestservice.converters;

import com.dwit.developers.springrestservice.dto.CategoryDto;
import com.dwit.developers.springrestservice.entity.Category;
import com.dwit.developers.springrestservice.entity.StationeryType;
import com.dwit.developers.springrestservice.repo.StationeryTypeRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter extends AbstractConverter<Category, CategoryDto> {
    public final StationeryTypeRepo stationeryTypeRepo;


    public CategoryConverter(StationeryTypeRepo stationeryTypeRepo) {
        this.stationeryTypeRepo = stationeryTypeRepo;
    }

    @Override
    public Category toEntity(CategoryDto categoryDto) {
       Category category = new Category();
       category.setId(categoryDto.getId());
       category.setName(categoryDto.getName());
       //for foreign key
        if(categoryDto.getStationeryTypeId() != null){
          StationeryType stationeryType =stationeryTypeRepo.findById(categoryDto.getStationeryTypeId() ).orElseThrow(() -> new RuntimeException("Warehouse not found with ID " +categoryDto.getStationeryTypeId()));
          category.setStationeryType(stationeryType);
        }

        return category;
    }

    @Override
   public List<Category> toEntityList(List<CategoryDto> categoryDtos) {
        return List.of();
    }

    @Override
   public List<CategoryDto> toDtoList(List<Category> categories) {
       List<CategoryDto> categoryDtoList = new ArrayList<>();
       for (Category category : categories) {
           CategoryDto categoryDto = toDto(category);
           categoryDtoList.add(categoryDto);
       }
       return categoryDtoList;
    }

    @Override
   public CategoryDto toDto(Category category) {
       CategoryDto categoryDto = new CategoryDto();
       categoryDto.setId(category.getId());
       categoryDto.setName(category.getName());
       if(category.getStationeryType() != null){
           categoryDto.setStationeryTypeId(category.getStationeryType().getId());
       }
       return categoryDto;
    }
}
