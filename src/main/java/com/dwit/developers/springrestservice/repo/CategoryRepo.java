package com.dwit.developers.springrestservice.repo;

import com.dwit.developers.springrestservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    public List<Category> findCategoriesByStationeryType(Integer stationeryTypeId);
}
