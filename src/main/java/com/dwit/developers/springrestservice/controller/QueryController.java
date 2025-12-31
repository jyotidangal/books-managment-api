package com.dwit.developers.springrestservice.controller;

import com.dwit.developers.springrestservice.constants.EntityConstant;
import com.dwit.developers.springrestservice.constants.ResponseMessageConstant;
import com.dwit.developers.springrestservice.dto.PaginationResultDto;
import com.dwit.developers.springrestservice.dto.ResponseDto;
import com.dwit.developers.springrestservice.handler.CustomMessageSource;
import com.dwit.developers.springrestservice.service.query.QueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query-controller")
public class QueryController extends BaseController {
    private final QueryService queryService;
    private final CustomMessageSource customMessageSource;


    public QueryController(QueryService queryService, CustomMessageSource customMessageSource) {
        this.queryService = queryService;
        this.customMessageSource = customMessageSource;
    }
    @GetMapping("/items/{id}")
    public ResponseEntity<ResponseDto> getItemsByWarehouseId(@PathVariable("id") Integer warehouseId, @RequestParam (defaultValue = "1") Integer pageNumber,@RequestParam (defaultValue = "5") Integer pageSize) {
        PaginationResultDto items = queryService.getItemsByWarehouseId(warehouseId, pageNumber, pageSize );

        if(!items.getItems().isEmpty())  {
            return ResponseEntity.ok(successResponse(customMessageSource.get(ResponseMessageConstant.FETCH_SUCCESS,customMessageSource.get(EntityConstant.ITEM)), items ));

        } else {
            return ResponseEntity.ok(failureResponse(customMessageSource.get(ResponseMessageConstant.FETCH_FAILURE,customMessageSource.get(EntityConstant.ITEM)), null));

        }
    }

}
