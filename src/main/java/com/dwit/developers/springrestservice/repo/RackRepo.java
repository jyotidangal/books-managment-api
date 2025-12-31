package com.dwit.developers.springrestservice.repo;

import com.dwit.developers.springrestservice.entity.Rack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RackRepo extends JpaRepository<Rack,Integer> {
    public List<Rack> findRackByRoomId(Integer roomId);
}
