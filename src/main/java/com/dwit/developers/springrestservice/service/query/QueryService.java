package com.dwit.developers.springrestservice.service.query;

import com.dwit.developers.springrestservice.dto.PaginationResultDto;
import com.dwit.developers.springrestservice.dto.ResultDto;

import java.util.List;

public interface QueryService {
    public PaginationResultDto  getItemsByWarehouseId(int warehouseId, Integer pageNumber, Integer pageSize);
}
