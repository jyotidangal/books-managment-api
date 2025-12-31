package com.dwit.developers.springrestservice.service.category;

import com.dwit.developers.springrestservice.converters.CategoryConverter;
import com.dwit.developers.springrestservice.dto.CategoryDto;
import com.dwit.developers.springrestservice.entity.Category;
import com.dwit.developers.springrestservice.repo.CategoryRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    public final CategoryRepo categoryRepo;
    public final CategoryConverter categoryConverter;

    public CategoryServiceImpl(CategoryRepo categoryRepo, CategoryConverter categoryConverter) {
        this.categoryRepo = categoryRepo;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public List<CategoryDto> findCategoriesByStationeryType(Integer stationeryTypeId) {
        List<Category> categoryList = categoryRepo.findCategoriesByStationeryType(stationeryTypeId);
        if(!categoryList.isEmpty()){
            return categoryConverter.toDtoList(categoryList);
        }
        else{
            return null;
        }

    }

    @Override
    public CategoryDto create(CategoryDto categoryDto) {
        Category category = categoryConverter.toEntity(categoryDto);
        Category saved = categoryRepo.save(category);
        return categoryConverter.toDto(saved);

    }

    @Override
    public CategoryDto findById(Integer integer) {
        Optional<Category> category = categoryRepo.findById(integer);
        return category.map(categoryConverter::toDto).orElse(null);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categoryList = categoryRepo.findAll();
        return categoryConverter.toDtoList(categoryList);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, Integer id) {
        Category category =categoryRepo.findById(id).orElse(null);
        if(category == null){
            return null;
        }
        category.setId(id);
        category.setName(categoryDto.getName());
        Category saved = categoryRepo.save(category);
        return categoryConverter.toDto(saved);

    }

    @Override
    public Boolean delete(Integer integer) {
        if(categoryRepo.findById(integer).isEmpty()){
            return false;
        }
        else{
            categoryRepo.deleteById(integer);
            return true;

        }

    }
}
