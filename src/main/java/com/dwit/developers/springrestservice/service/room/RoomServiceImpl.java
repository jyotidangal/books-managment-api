package com.dwit.developers.springrestservice.service.room;

import com.dwit.developers.springrestservice.converters.RoomConverter;
import com.dwit.developers.springrestservice.dto.RoomDto;
import com.dwit.developers.springrestservice.entity.Room;
import com.dwit.developers.springrestservice.entity.StationeryType;
import com.dwit.developers.springrestservice.entity.Warehouse;
import com.dwit.developers.springrestservice.repo.RoomRepo;
import com.dwit.developers.springrestservice.repo.WarehouseRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    public final RoomRepo roomRepo;
    public final RoomConverter roomConverter;
    public final WarehouseRepo warehouseRepo;

    public RoomServiceImpl(RoomRepo roomRepo, RoomConverter roomConverter, WarehouseRepo warehouseRepo) {
        this.roomRepo = roomRepo;
        this.roomConverter = roomConverter;
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        Room room = roomConverter.toEntity(roomDto);
        roomRepo.save(room);
        return roomConverter.toDto(room);
    }

    @Override
    public RoomDto findById(Integer integer) {
        Optional<Room> optionalRoom = roomRepo.findById(integer);
        return optionalRoom.map(roomConverter::toDto).orElse(null);
    }

    @Override
    public List<RoomDto> findAll() {
        List<Room> room = roomRepo.findAll();
        return roomConverter.toDtoList(room);
    }

    @Override
    public RoomDto update(RoomDto roomDto, Integer id) {
        Room room =roomRepo.findById(id)
                .orElse(null);

        if (room == null) {
            return null; // controller handles failed response
        }

        // Update fields manually
        room.setId(id);
        room.setName(roomDto.getName());
        room.setKeyNumber(roomDto.getKeyNumber());

        // Set foreign key properly
        if (roomDto.getWarehouseId() != null) {
            Warehouse warehouse = warehouseRepo.findById(roomDto.getWarehouseId())
                    .orElseThrow(() -> new RuntimeException(
                            "Warehouse not found with id: " + roomDto.getWarehouseId()));
            room.setWarehouse(warehouse);
        }
        Room saved = roomRepo.save(room);
        return roomConverter.toDto(saved);
    }

    @Override
    public Boolean delete(Integer integer) {
        if(roomRepo.existsById(integer)) {
            roomRepo.deleteById(integer);
            return true;
        }
        else{
            return false;
        }

    }





    @Override
    public List<RoomDto> findByWarehouseId(Integer warehouseId) {
        List<Room> room = roomRepo.findByWarehouseId(warehouseId);
        if (!room.isEmpty()) {
            return roomConverter.toDtoList(room);
        } else {
            return null;
        }

    }
}
