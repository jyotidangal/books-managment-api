package com.dwit.developers.springrestservice.mapper;

import com.dwit.developers.springrestservice.dto.PaginationResultDto;
import com.dwit.developers.springrestservice.dto.ResultDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface ItemsMapper {
    List<ResultDto> getItemsByWarehouseId(@Param("warehouseId") Integer warehouseId, @Param("limit") Integer limit, @Param("offset") Integer offset);
    int getItemCountByWarehouseId(@Param("warehouseId") int warehouseId);

}
