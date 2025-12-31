package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.dto.RoomDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.room.RoomService;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/room")
public class RoomController extends BaseController{
    public final RoomService roomService;
    public final CustomMessageSource customMessageSource;

    public RoomController(RoomService roomService, CustomMessageSource customMessageSource) {
        this.roomService = roomService;
        this.customMessageSource = customMessageSource;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody RoomDto roomDto){
         roomDto = roomService.create(roomDto);

        if (roomDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.STATIONERY_TYPE)), roomDto));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.STATIONERY_TYPE)),null));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDto> findAll() {
        List<RoomDto> roomDtoDtoList = roomService.findAll();
        if (!roomDtoDtoList.isEmpty()) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.ROOM)), roomDtoDtoList ));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.ROOM)), new ArrayList<>()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {
        RoomDto roomDto = roomService.findById(id);
        if (roomDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.ROOM)), roomDto ));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.ROOM)), new ArrayList<>()));
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseDto> Update(@PathVariable("id") Integer id, @Valid @RequestBody RoomDto roomDto) {
      RoomDto  updated = roomService.update(roomDto, id);
        if (updated != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_SUCCESS,customMessageSource.get(EntityConstant.ROOM)), updated ));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_FAILED,customMessageSource.get(EntityConstant.ROOM)), new ArrayList<>()));
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer id){
        Boolean status = roomService.delete(id);
        if (status){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.DELETE_SUCCESS,customMessageSource.get(EntityConstant.ROOM)),null));
        }
        return ResponseEntity.ok(failureResponse("Id not found", null));
    }



}
