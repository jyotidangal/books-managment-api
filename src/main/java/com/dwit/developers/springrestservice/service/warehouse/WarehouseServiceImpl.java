package com.dwit.developers.springrestservice.service.warehouse;

import com.dwit.developers.springrestservice.converters.WarehouseConverter;
import com.dwit.developers.springrestservice.dto.WarehouseDto;
import com.dwit.developers.springrestservice.entity.Warehouse;
import com.dwit.developers.springrestservice.repo.WarehouseRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepo warehouseRepo;
    private final WarehouseConverter warehouseConverter;

    public WarehouseServiceImpl(WarehouseRepo warehouseRepo,
                                WarehouseConverter warehouseConverter) {
        this.warehouseRepo = warehouseRepo;
        this.warehouseConverter = warehouseConverter;
    }

    @Override
    public WarehouseDto create(WarehouseDto warehouseDto) {
        Warehouse warehouse = warehouseConverter.toEntity(warehouseDto);
        warehouse = warehouseRepo.save(warehouse);
        return warehouseConverter.toDto(warehouse);
    }

    @Override
    public WarehouseDto findById(Integer integer) {
        Optional<Warehouse> optionalWarehouse = warehouseRepo.findById(integer);
        if (optionalWarehouse.isPresent()) {
            return warehouseConverter.toDto(optionalWarehouse.get());
        }
        return null;
    }

    @Override
    public List<WarehouseDto> findAll() {
        List<Warehouse> warehouses = warehouseRepo.findAll();
        return warehouseConverter.toDtoList(warehouses);
    }

    @Override
    public WarehouseDto update(WarehouseDto warehouseDto, Integer id) {

        Warehouse warehouse = warehouseRepo.findById(id)
                .orElse(null);

        if (warehouse == null) {
            return null; // controller handles failed response
        }

        // Update fields manually
        warehouse.setId(id);
        warehouse.setName(warehouseDto.getName());
        warehouse.setLocation(warehouseDto.getLocation());

        // Add other fields as needed

        Warehouse saved = warehouseRepo.save(warehouse);

        return warehouseConverter.toDto(saved);
    }


    @Override
    public Boolean delete(Integer integer) {
        if(warehouseRepo.existsById(integer)){
            warehouseRepo.deleteById(integer);
            return true;
        }
        else{
            return false;
        }

    }
//
//    @Override
//    public List<String> generateValue(List<Integer> warehouseIdList) {
//        List<String> myList = new ArrayList<>();
//                for(Integer warehouseId : warehouseIdList){
//                    Thread thread =new Thread(()->{generateNumbers(warehouseId);});
//                   String generatedData= generateNumbers(warehouseId);
//                   myList.add(generatedData);
//                }
//
//        return myList;
//    }
//    private String generateNumbers(Integer warehouseId){
//     Warehouse warehouse =   warehouseRepo.findById(warehouseId).get();
//     int i=0;
//     String generatedData = warehouse.getId()+"_1111"+warehouse.getId()+"_2222"+warehouse.getId()+"_3333";
//     try{
//         Thread.sleep(500);
//     }catch (Exception e){
//
//     }
//     warehouse.setId(i++);
//     warehouseRepo.save(warehouse);
//     return generatedData;
//
//
//
//
//
//    }
//
//    // additional methods
}
