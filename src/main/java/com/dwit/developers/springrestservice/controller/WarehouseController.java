package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.dto.WarehouseDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.warehouse.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ware-house")
public class WarehouseController extends BaseController {

    private final WarehouseService warehouseService;
    private final CustomMessageSource customMessageSource;

    public WarehouseController(WarehouseService warehouseService, CustomMessageSource customMessageSource) {

        this.warehouseService = warehouseService;
        this.customMessageSource = customMessageSource;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createWarehouse(@Valid @RequestBody WarehouseDto warehouseDto) {
        warehouseDto = warehouseService.create(warehouseDto);
        if (warehouseDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.WAREHOUSE)), warehouseDto));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.WAREHOUSE)),null));
        }
    }
//
//    @PostMapping("/generate")
//    public ResponseEntity<ResponseDto> generateNumbers(@RequestBody List<Integer> warehouseIdList) {
//        List<String> warehouseGeneratevalue = warehouseService.generateValue(warehouseIdList);
//        if (!warehouseGeneratevalue.isEmpty()) {
//            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.WAREHOUSE)), null));
//        } else {
//            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.WAREHOUSE)),null));
//        }
//    }



    @GetMapping
    public ResponseEntity<ResponseDto> findAll() {
        List<WarehouseDto> warehouseDtoList = warehouseService.findAll();
        if (!warehouseDtoList.isEmpty()) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.WAREHOUSE)), warehouseDtoList));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.WAREHOUSE)), warehouseDtoList));

        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {
        WarehouseDto warehouseDto = warehouseService.findById(id);
        if (warehouseDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.WAREHOUSE)), warehouseDto));

        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.WAREHOUSE)), new ArrayList<>()));
        }
    }
    // to update warehouse
    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseDto> Update(@PathVariable("id") Integer id, @Valid @RequestBody WarehouseDto warehouseDto) {
        WarehouseDto updated = warehouseService.update(warehouseDto, id);
        if (updated != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_SUCCESS,customMessageSource.get(EntityConstant.WAREHOUSE)), updated ));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_FAILED,customMessageSource.get(EntityConstant.WAREHOUSE)), new ArrayList<>()));

        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer id) {
        Boolean status = warehouseService.delete(id);
        if (status) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.DELETE_SUCCESS,customMessageSource.get(EntityConstant.WAREHOUSE)), null));
        } else {
            return ResponseEntity.ok(failureResponse("Id not found", null));
        }


    }


}
