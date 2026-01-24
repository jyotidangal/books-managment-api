package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.dto.StationeryTypeDto;
import com.dwit.developers.springrestservice.dto.WarehouseDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.stationerytype.StationeryTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/stationery-type")
public class StationeryTypeController extends BaseController{
    private final StationeryTypeService stationeryTypeService;
    private final CustomMessageSource customMessageSource;

    public StationeryTypeController(StationeryTypeService stationeryTypeService, CustomMessageSource customMessageSource) {
        this.stationeryTypeService = stationeryTypeService;
        this.customMessageSource = customMessageSource;
    }


    @PostMapping
    public ResponseEntity<ResponseDto>createstationeryType(@Valid  @RequestBody StationeryTypeDto stationeryTypeDto){
        stationeryTypeDto = stationeryTypeService.create(stationeryTypeDto);
        if (stationeryTypeDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), stationeryTypeDto));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.STATIONERY_TYPE)),null));
        }

    }

    @GetMapping
    public ResponseEntity<ResponseDto> findAll() {
        List<StationeryTypeDto> stationeryTypeDtoList = stationeryTypeService.findAll();
        if (!stationeryTypeDtoList.isEmpty()) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), stationeryTypeDtoList));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), new ArrayList<>()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {
        StationeryTypeDto stationeryTypeDto = stationeryTypeService.findById(id);
        if (stationeryTypeDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), stationeryTypeDto));
        }
        else
        {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), null));
        }
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseDto> Update(@PathVariable("id") Integer id, @Valid @RequestBody StationeryTypeDto stationeryTypeDto) {
        StationeryTypeDto updated = stationeryTypeService.update(stationeryTypeDto, id);
        if (updated != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_SUCCESS,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), updated ));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_FAILED,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), new ArrayList<>()));
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer id){
        Boolean status = stationeryTypeService.delete(id);
        if (status){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.DELETE_SUCCESS,customMessageSource.get(EntityConstant.STATIONERY_TYPE)),null));
        }
        return ResponseEntity.ok(failureResponse("Id not found", null));
    }


}
