package com.dwit.developers.springrestservice.service.stationerytype;

import com.dwit.developers.springrestservice.converters.StationeryTypeConverter;
import com.dwit.developers.springrestservice.dto.StationeryTypeDto;
import com.dwit.developers.springrestservice.dto.WarehouseDto;
import com.dwit.developers.springrestservice.entity.StationeryType;
import com.dwit.developers.springrestservice.entity.Warehouse;
import com.dwit.developers.springrestservice.repo.StationeryTypeRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StationeryTypeServiceImpl implements StationeryTypeService {
    public final StationeryTypeRepo stationeryTypeRepo;
    public final StationeryTypeConverter stationeryTypeConverter;

    public StationeryTypeServiceImpl(StationeryTypeRepo stationeryTypeRepo, StationeryTypeConverter stationeryTypeConverter) {
        this.stationeryTypeRepo = stationeryTypeRepo;
        this.stationeryTypeConverter = stationeryTypeConverter;
    }

    @Override
    public StationeryTypeDto create(StationeryTypeDto stationeryTypeDto) {
       StationeryType stationeryType = stationeryTypeConverter.toEntity(stationeryTypeDto);
       stationeryType = stationeryTypeRepo.save(stationeryType);
       return stationeryTypeConverter.toDto(stationeryType);

    }

    @Override
    public StationeryTypeDto findById(Integer integer) {
      Optional<StationeryType> stationeryTypeOptional = stationeryTypeRepo.findById(integer);
      if(stationeryTypeOptional.isPresent()) {
          return stationeryTypeConverter.toDto(stationeryTypeOptional.get());
      }
      else{
          return null;
      }
    }

    @Override
    public List<StationeryTypeDto> findAll() {
        List<StationeryType> stationeryTypes = stationeryTypeRepo.findAll();
        return stationeryTypeConverter.toDtoList(stationeryTypes);


    }

    @Override
    public StationeryTypeDto update(StationeryTypeDto stationeryTypeDto, Integer id) {
      StationeryType stationeryType=stationeryTypeRepo.findById(id)
                .orElse(null);

        if (stationeryType == null) {
            return null; // controller handles failed response
        }

        // Update fields manually
        stationeryType.setId(id);
        stationeryType.setName(stationeryTypeDto.getName());

        StationeryType saved = stationeryTypeRepo.save(stationeryType);
        return stationeryTypeConverter.toDto(saved);
    }

    @Override
    public Boolean delete(Integer integer) {
        if(stationeryTypeRepo.existsById(integer)) {
            stationeryTypeRepo.deleteById(integer);
            return true;
        }
        else{
            return false;
        }
    }
}
