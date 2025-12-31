package com.dwit.developers.springrestservice.converters;

import com.dwit.developers.springrestservice.dto.StationeryTypeDto;
import com.dwit.developers.springrestservice.entity.StationeryType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StationeryTypeConverter extends AbstractConverter<StationeryType, StationeryTypeDto> {
    @Override
    public StationeryType toEntity(StationeryTypeDto stationeryTypeDto) {
        StationeryType stationeryType = new StationeryType();
        stationeryType.setName(stationeryTypeDto.getName());
        return stationeryType;
    }
    @Override
    public List<StationeryType> toEntityList(List<StationeryTypeDto> stationeryTypeDtos) {
        return List.of();
    }

    @Override
    public List<StationeryTypeDto> toDtoList(List<StationeryType> stationeryTypes) {
        List<StationeryTypeDto> stationeryTypeDtoList = new ArrayList<>();
        for (StationeryType stationeryType : stationeryTypes) {
            stationeryTypeDtoList.add(toDto(stationeryType));
        }
        return stationeryTypeDtoList;
    }

    @Override
    public StationeryTypeDto toDto(StationeryType stationeryType) {
        StationeryTypeDto stationeryTypeDto = new StationeryTypeDto();
        stationeryTypeDto.setId(stationeryType.getId());
        stationeryTypeDto.setName(stationeryType.getName());

        return stationeryTypeDto;
    }
}
