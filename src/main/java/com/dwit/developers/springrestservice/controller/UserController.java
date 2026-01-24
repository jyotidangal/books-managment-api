package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.dto.UserDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class UserController extends BaseController {
 private final UserService userService;
    private final CustomMessageSource customMessageSource;


    public UserController(UserService userService, CustomMessageSource customMessageSource) {
        this.userService = userService;
        this.customMessageSource = customMessageSource;
    }
    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto userDto){
        userDto = userService.create(userDto);
        if(userDto != null){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.CREATE_SUCCESS,customMessageSource.get(EntityConstant.Registration)),userDto));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.CREATE_FAILED,customMessageSource.get(EntityConstant.Registration)),null));
        }
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getUser(){
        List<UserDto> userDtoList = userService.findAll();
        if(!userDtoList.isEmpty()){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.User)), userDtoList));
        }
        else{
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE, customMessageSource.get(EntityConstant.User)), null));
        }

    }
    @DeleteMapping("id/{id}")
    public ResponseEntity<ResponseDto> deleteById(@PathVariable Long id){
        Boolean status = userService.delete(id);
        if (status){
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.DELETE_SUCCESS,customMessageSource.get(EntityConstant.User)),null));
        }
        return ResponseEntity.ok(failureResponse("User not found!!", null));
    }

}
