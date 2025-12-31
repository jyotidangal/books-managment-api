package com.dwit.developers.springrestservice.converters;

import com.dwit.developers.springrestservice.dto.RackDto;
import com.dwit.developers.springrestservice.entity.Rack;
import com.dwit.developers.springrestservice.entity.Room;
import com.dwit.developers.springrestservice.repo.RoomRepo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RackConverter extends AbstractConverter<Rack, RackDto> {
    public final RoomRepo roomRepo;

    public RackConverter(RoomRepo roomRepo) {

        this.roomRepo = roomRepo;
    }

    @Override
   public Rack toEntity(RackDto rackDto) {
       Rack rack = new Rack();
       rack.setRackName(rackDto.getName());
       rack.setNumberOfBrackets(rackDto.getNumberOfBrackets());
       rack.setLength(rackDto.getLength());
       rack.setBreadth(rackDto.getBreadth());
       //for foreign key room id
        if(rackDto.getRoomId()!= null){
           Room room=roomRepo.findById(rackDto.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Warehouse not found with ID " +rackDto.getRoomId()));
            rack.setRoom(room);
        }
        return rack;
    }

    @Override
    public List<Rack> toEntityList(List<RackDto> rackDtos) {
        return List.of();
    }

    @Override
   public List<RackDto> toDtoList(List<Rack> racks) {
        List<RackDto> rackDtoList = new ArrayList<>();
        for (Rack rack : racks) {
            RackDto rackDto = toDto(rack);
            rackDtoList.add(rackDto);
        }
        return rackDtoList;

    }

    @Override
   public RackDto toDto(Rack rack) {
        RackDto rackDto = new RackDto();
        rackDto.setId(rack.getId());
        rackDto.setName(rack.getRackName());
        rackDto.setNumberOfBrackets(rack.getNumberOfBrackets());
        rackDto.setLength(rack.getLength());
        rackDto.setBreadth(rack.getBreadth());
        if(rack.getRoom() != null){
            rackDto.setRoomId(rack.getRoom().getId());
        }
        return rackDto;
    }
}
