package com.dwit.developers.springrestservice.converters;

import com.dwit.developers.springrestservice.dto.RoomDto;
import com.dwit.developers.springrestservice.entity.Room;
import com.dwit.developers.springrestservice.entity.Warehouse;
import com.dwit.developers.springrestservice.repo.WarehouseRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomConverter extends AbstractConverter<Room, RoomDto> {
    public final WarehouseRepo warehouseRepo;

    public RoomConverter(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public Room toEntity(RoomDto roomDto) {
        Room room = new Room();
        room.setName(roomDto.getName());
        room.setKeyNumber(roomDto.getKeyNumber());
        // for foreign key
        if (roomDto.getWarehouseId() != null) {
            Warehouse entity = warehouseRepo.findById(roomDto.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found with ID " + roomDto.getWarehouseId()));
            room.setWarehouse(entity);
        }

        return room;
    }

    @Override
    public List<Room> toEntityList(List<RoomDto> roomDtos) {
        return List.of();
    }

    @Override
    public List<RoomDto> toDtoList(List<Room> rooms) {
        List<RoomDto> roomDtoList = new ArrayList<>();
        for (Room room : rooms) {
            RoomDto roomDto = toDto(room);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;

    }

    @Override
    public RoomDto toDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setName(room.getName());
        roomDto.setKeyNumber(room.getKeyNumber());
        if (room.getWarehouse() != null) {
            roomDto.setWarehouseId(room.getWarehouse().getId());
        }
        return roomDto;




    }
}
