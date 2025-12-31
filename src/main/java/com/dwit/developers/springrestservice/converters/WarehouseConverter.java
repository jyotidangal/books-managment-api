package com.dwit.developers.springrestservice.converters;

import com.dwit.developers.springrestservice.dto.WarehouseDto;
import com.dwit.developers.springrestservice.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WarehouseConverter extends AbstractConverter<Warehouse, WarehouseDto> {


    @Override
    public Warehouse toEntity(WarehouseDto warehouseDto) {
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        warehouse.setLocation(warehouseDto.getLocation());
        return warehouse;
    }

    @Override
    public List<Warehouse> toEntityList(List<WarehouseDto> warehouseDtos) {
        return List.of();
    }

    @Override
    public List<WarehouseDto> toDtoList(List<Warehouse> warehouses) {
        List<WarehouseDto> warehouseDtoList = new ArrayList<>();
        for (Warehouse warehouse : warehouses) {
            warehouseDtoList.add(toDto(warehouse));
        }
        return warehouseDtoList;
    }

    @Override
    public WarehouseDto toDto(Warehouse warehouse) {
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(warehouse.getId());
        warehouseDto.setName(warehouse.getName());
        warehouseDto.setLocation(warehouse.getLocation());
        return warehouseDto;
    }
}
