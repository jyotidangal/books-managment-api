package com.dwit.developers.springrestservice.service;

import java.util.List;

public interface GenericService<DTO, ID> {

    DTO create(DTO dto);

    DTO findById(ID id);

    List<DTO> findAll();

    DTO update(DTO dto,ID id);

    Boolean delete(ID id);
}
