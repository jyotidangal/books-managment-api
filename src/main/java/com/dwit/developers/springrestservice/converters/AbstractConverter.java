package com.dwit.developers.springrestservice.converters;

import java.util.List;

public abstract class AbstractConverter<ENTITY, DTO> {
    abstract ENTITY toEntity(DTO dto);

    abstract List<ENTITY> toEntityList(List<DTO> dtoList);

    abstract List<DTO> toDtoList(List<ENTITY> entities);

    abstract DTO toDto(ENTITY entity);
}
