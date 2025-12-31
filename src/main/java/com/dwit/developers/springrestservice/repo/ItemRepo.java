package com.dwit.developers.springrestservice.repo;

import com.dwit.developers.springrestservice.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item,Integer> {
}
