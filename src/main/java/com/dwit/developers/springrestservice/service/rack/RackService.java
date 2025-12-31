package com.dwit.developers.springrestservice.service.rack;

import com.dwit.developers.springrestservice.dto.RackDto;
import com.dwit.developers.springrestservice.service.GenericService;

import java.util.List;

public interface RackService extends GenericService<RackDto,Integer> {
    public List<RackDto> findRackByRoomId(Integer roomId);
}
