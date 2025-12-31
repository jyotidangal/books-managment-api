package com.dwit.developers.springrestservice.repo;


import com.dwit.developers.springrestservice.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepo extends JpaRepository<Warehouse,Integer> {
}
