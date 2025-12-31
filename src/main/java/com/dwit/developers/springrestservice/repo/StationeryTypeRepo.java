package com.dwit.developers.springrestservice.repo;

import com.dwit.developers.springrestservice.entity.StationeryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationeryTypeRepo extends JpaRepository<StationeryType, Integer> {
}
