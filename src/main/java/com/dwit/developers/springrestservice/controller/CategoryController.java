package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.dto.CategoryDto;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.category.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController extends BaseController {
    public final CategoryService categoryService;
    public final CustomMessageSource customMessageSource;

    public CategoryController(CategoryService categoryService, CustomMessageSource customMessageSource) {
        this.categoryService = categoryService;
        this.customMessageSource = customMessageSource;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){

        CategoryDto saved= categoryService.create(categoryDto);

        if (saved != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.CATEGORY)), saved));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.CATEGORY)),null));
        }
    }
    @GetMapping
    public ResponseEntity<ResponseDto> findAll() {
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        if (!categoryDtoList.isEmpty()) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.RACK)), categoryDtoList));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.RACK)), new ArrayList<>()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {

        CategoryDto categoryDto = categoryService.findById(id);
        if (categoryDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.CATEGORY)),categoryDto ));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.CATEGORY)), new ArrayList<>()));
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseDto> Update(@PathVariable("id") Integer id, @Valid @RequestBody CategoryDto categoryDto) {

        CategoryDto updated = categoryService.update(categoryDto,id);
        if (updated != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_SUCCESS,customMessageSource.get(EntityConstant.CATEGORY)), updated ));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_FAILED,customMessageSource.get(EntityConstant.CATEGORY)), new ArrayList<>()));
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer id){
        Boolean status = categoryService.delete(id);
        if (status){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.DELETE_SUCCESS,customMessageSource.get(EntityConstant.CATEGORY)),null));
        }
        return ResponseEntity.ok(failureResponse("Id not found", null));
    }







}
