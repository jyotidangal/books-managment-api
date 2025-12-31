package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.dto.RackDto;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.dto.RoomDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.rack.RackService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rack")
public class RackController extends BaseController{
    public final RackService rackService;
    public final CustomMessageSource customMessageSource;

    public RackController(RackService rackService, CustomMessageSource customMessageSource) {
        this.rackService = rackService;
        this.customMessageSource = customMessageSource;
    }
    @PostMapping
    public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody RackDto rackDto){
       rackDto = rackService.create(rackDto);

        if (rackDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.RACK)), rackDto));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.RACK)),null));
        }
    }


    @GetMapping
    public ResponseEntity<ResponseDto> findAll() {
        List<RackDto> rackDtoList = rackService.findAll();
        if (!rackDtoList.isEmpty()) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.RACK)), rackDtoList ));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.RACK)), new ArrayList<>()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {

        RackDto rackDto = rackService.findById(id);
        if (rackDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.RACK)),rackDto ));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.RACK)), new ArrayList<>()));
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseDto> Update(@PathVariable("id") Integer id, @Valid @RequestBody RackDto rackDto) {

        RackDto updated = rackService.update(rackDto,id);
        if (updated != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_SUCCESS,customMessageSource.get(EntityConstant.RACK)), updated ));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_FAILED,customMessageSource.get(EntityConstant.RACK)), new ArrayList<>()));
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer id){
        Boolean status = rackService.delete(id);
        if (status){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.DELETE_SUCCESS,customMessageSource.get(EntityConstant.RACK)),null));
        }
        return ResponseEntity.ok(failureResponse("Id not found", null));
    }


}
