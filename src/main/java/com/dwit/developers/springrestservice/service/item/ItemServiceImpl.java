package com.dwit.developers.springrestservice.service.item;

import com.dwit.developers.springrestservice.converters.ItemConverter;
import com.dwit.developers.springrestservice.dto.ItemDto;
import com.dwit.developers.springrestservice.entity.Category;
import com.dwit.developers.springrestservice.entity.Item;
import com.dwit.developers.springrestservice.entity.Rack;
import com.dwit.developers.springrestservice.repo.CategoryRepo;
import com.dwit.developers.springrestservice.repo.ItemRepo;
import com.dwit.developers.springrestservice.repo.RackRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    public final ItemRepo itemRepo;
    public final ItemConverter itemConverter;
    public final RackRepo rackRepo;
    public final CategoryRepo categoryRepo;

    public ItemServiceImpl(ItemRepo itemRepo, ItemConverter itemConverter, RackRepo rackRepo, CategoryRepo categoryRepo) {
        this.itemRepo = itemRepo;
        this.itemConverter = itemConverter;
        this.rackRepo = rackRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public ItemDto create(ItemDto itemDto) {
       Item item =itemConverter.toEntity(itemDto);
        itemRepo.save(item);
        return itemConverter.toDto(item);
    }

    @Override
    public ItemDto findById(Integer integer) {
        Optional<Item> item = itemRepo.findById(integer);
        return item.map(itemConverter::toDto).orElse(null);

    }

    @Override
    public List<ItemDto> findAll() {
       List<Item > items = itemRepo.findAll();
       return itemConverter.toDtoList(items);
    }

    @Override
    public ItemDto update(ItemDto itemDto, Integer id) {
        Item item = itemRepo.findById(id).orElse(null);
        if(item == null){
           return null;
        }
        item.setName(itemDto.getName());
        if(itemDto.getCategoryId() != null) {
            Category category = categoryRepo.findById(itemDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"+itemDto.getCategoryId()));
            item.setCategory(category);
        }
        if(itemDto.getRackId() != null) {
            Rack rack =rackRepo.findById(itemDto.getRackId()).orElseThrow(() -> new RuntimeException("rack not found"+itemDto.getRackId()));
            item.setRack(rack);
        }
        Item saved = itemRepo.save(item);
        return itemConverter.toDto(saved);

    }

    @Override
    public Boolean delete(Integer integer) {
       if(itemRepo.findById(integer).isPresent()){
           itemRepo.deleteById(integer);
           return true;
       }
        return false;


    }
}
