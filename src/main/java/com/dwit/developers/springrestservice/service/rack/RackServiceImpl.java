package com.dwit.developers.springrestservice.service.rack;

import com.dwit.developers.springrestservice.converters.RackConverter;
import com.dwit.developers.springrestservice.dto.RackDto;
import com.dwit.developers.springrestservice.entity.Rack;
import com.dwit.developers.springrestservice.entity.Room;
import com.dwit.developers.springrestservice.entity.Warehouse;
import com.dwit.developers.springrestservice.repo.RackRepo;
import com.dwit.developers.springrestservice.repo.RoomRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RackServiceImpl implements RackService {
    public  final RackRepo rackRepo;
    public final RackConverter rackConverter;
    public  final RoomRepo roomRepo ;

    public RackServiceImpl(RackRepo rackRepo, RackConverter rackConverter, RoomRepo roomRepo) {
        this.rackRepo = rackRepo;
        this.rackConverter = rackConverter;
        this.roomRepo = roomRepo;
    }

    @Override
    public RackDto create(RackDto rackDto) {
        Rack rack = rackConverter.toEntity(rackDto);
         rackRepo.save(rack);
        return rackConverter.toDto(rack);
    }

    @Override
    public RackDto findById(Integer integer) {
        Optional<Rack> rack = rackRepo.findById(integer);
        return rack.map(rackConverter::toDto).orElse(null);


    }

    @Override
    public List<RackDto> findAll() {
       List<Rack> racks = rackRepo.findAll();
       return rackConverter.toDtoList(racks);
    }

    @Override
    public RackDto update(RackDto rackDto, Integer id) {

       Rack rack=rackRepo.findById(id)
                .orElse(null);
        if (rack== null) {
            return null; // controller handles failed response
        }
        rack.setId(id);
        rack.setRackName(rackDto.getName());
        rack.setNumberOfBrackets(rackDto.getNumberOfBrackets());
        rack.setLength(rackDto.getLength());
        rack.setBreadth(rackDto.getBreadth());
        //for foreign key room id
        if(rackDto.getRoomId()!= null){
            Room room=roomRepo.findById(rackDto.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with ID " +rackDto.getRoomId()));
            rack.setRoom(room);
        }
        Rack saved = rackRepo.save(rack);
        return rackConverter.toDto(saved);
    }

    @Override
    public Boolean delete(Integer integer) {
        if(rackRepo.existsById(integer)) {
            rackRepo.deleteById(integer);
            return true;
        }
        else{
            return false;
        }


    }

    @Override
    public List<RackDto> findRackByRoomId(Integer roomId) {
        List<Rack> racks = rackRepo.findRackByRoomId(roomId);
        if(!racks.isEmpty()){
            return rackConverter.toDtoList(racks);
        }
        else{
            return null;
        }
    }
}
