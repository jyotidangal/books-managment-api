package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.dto.ItemDto;
import com.dwit.developers.springrestservice.dto.RackDto;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.item.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController extends BaseController {
    public final ItemService itemService;
    public final CustomMessageSource customMessageSource;

    public ItemController(ItemService itemService, CustomMessageSource customMessageSource) {
        this.itemService = itemService;
        this.customMessageSource = customMessageSource;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createRoom(@Valid @RequestBody ItemDto itemDto){

        itemDto = itemService.create(itemDto);

        if (itemDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.ITEM)), itemDto));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.ITEM)),null));
        }
    }


    @GetMapping
    public ResponseEntity<ResponseDto> findAll() {
        List<ItemDto> itemDtoList = itemService.findAll();
        if (!itemDtoList.isEmpty()) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.ITEM)), itemDtoList ));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.ITEM)), new ArrayList<>()));
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable Integer id) {

        ItemDto itemDto = itemService.findById(id);
        if (itemDto != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.ITEM)),itemDto ));
        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.ITEM)), null));
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ResponseDto> Update(@PathVariable("id") Integer id, @Valid @RequestBody ItemDto itemDto) {

        ItemDto updated = itemService.update(itemDto,id);
        if (updated != null) {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_SUCCESS,customMessageSource.get(EntityConstant.ITEM)), updated ));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.UPDATE_FAILED,customMessageSource.get(EntityConstant.ITEM)), new ArrayList<>()));
        }
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer id){
        Boolean status = itemService.delete(id);
        if (status){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.DELETE_SUCCESS,customMessageSource.get(EntityConstant.ITEM)),null));
        }
        return ResponseEntity.ok(failureResponse("Id not found", null));
    }






}
