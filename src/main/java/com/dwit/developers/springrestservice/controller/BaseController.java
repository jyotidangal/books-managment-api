package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.dto.ResponseDto;

public abstract class BaseController {

    public ResponseDto successResponse(String message, Object data) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(message);
        responseDto.setData(data);
        responseDto.setStatus(Boolean.TRUE);
        return responseDto;
    }

    public ResponseDto failureResponse(String message, Object data) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(message);
        responseDto.setData(data);
        responseDto.setStatus(Boolean.FALSE);
        return responseDto;
    }
}
