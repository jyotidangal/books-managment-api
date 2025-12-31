package com.dwit.developers.springrestservice.repo;

import com.dwit.developers.springrestservice.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomRepo extends JpaRepository<Room, Integer> {
    List<Room> findByWarehouseId(Integer warehouseId);

}
