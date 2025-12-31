package com.dwit.developers.springrestservice.service.room;

import com.dwit.developers.springrestservice.dto.RoomDto;
import com.dwit.developers.springrestservice.service.GenericService;

import java.util.List;

public interface RoomService extends GenericService<RoomDto, Integer> {
    public List<RoomDto> findByWarehouseId(Integer warehouseId);
}
