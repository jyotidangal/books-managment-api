package com.dwit.developers.springrestservice.converters;

import com.dwit.developers.springrestservice.dto.ItemDto;
import com.dwit.developers.springrestservice.entity.Category;
import com.dwit.developers.springrestservice.entity.Item;
import com.dwit.developers.springrestservice.entity.Rack;
import com.dwit.developers.springrestservice.repo.CategoryRepo;
import com.dwit.developers.springrestservice.repo.RackRepo;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ItemConverter extends AbstractConverter<Item, ItemDto> {
    public final RackRepo rackRepo;
    public final CategoryRepo categoryRepo;

    public ItemConverter(RackRepo rackRepo, CategoryRepo categoryRepo) {
        this.rackRepo = rackRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
   public Item toEntity(ItemDto itemDto) {
       Item item = new Item();
       item.setName(itemDto.getName());
       //for foreign keys
        if(itemDto.getCategoryId() != null) {
            Category category = categoryRepo.findById(itemDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"+itemDto.getCategoryId()));
            item.setCategory(category);
        }
        if(itemDto.getRackId() != null) {
            Rack rack =rackRepo.findById(itemDto.getRackId()).orElseThrow(() -> new RuntimeException("rack not found"+itemDto.getRackId()));
            item.setRack(rack);
        }
        return item;
    }

   public @Override
    List<Item> toEntityList(List<ItemDto> itemDtos) {
        return List.of();
    }

   public @Override
    List<ItemDto> toDtoList(List<Item> items) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for (Item item : items) {
            ItemDto itemDto = toDto(item);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }

   public @Override
    ItemDto toDto(Item item) {
       ItemDto itemDto = new ItemDto();
       itemDto.setId(item.getId());
       itemDto.setName(item.getName());
       if(item.getCategory() != null) {
           itemDto.setCategoryId(item.getCategory().getId());
       }
       if(item.getRack() != null) {
           itemDto.setRackId(item.getRack().getId());
       }
       return itemDto;
    }
}
