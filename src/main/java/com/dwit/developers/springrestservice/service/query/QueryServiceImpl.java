package com.dwit.developers.springrestservice.service.query;

import com.dwit.developers.springrestservice.dto.PaginationResultDto;
import com.dwit.developers.springrestservice.dto.ResultDto;
import com.dwit.developers.springrestservice.exception.PageOutOfRangeException;
import com.dwit.developers.springrestservice.mapper.ItemsMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QueryServiceImpl implements QueryService {
    public final ItemsMapper itemsMapper;

    public QueryServiceImpl(ItemsMapper itemsMapper) {
        this.itemsMapper = itemsMapper;
    }

    @Override
    public PaginationResultDto getItemsByWarehouseId(int warehouseId, Integer pageNumber, Integer pageSize) {
        int offset = (pageNumber - 1) * pageSize;

        // Fetch paginated items
        List<ResultDto> items = itemsMapper.getItemsByWarehouseId(warehouseId, pageSize, offset);

        // Fetch total count
        int totalItems = itemsMapper.getItemCountByWarehouseId(warehouseId);

        // Calculate total pages
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);
        if (pageNumber > totalPages && totalPages != 0) {
            throw new PageOutOfRangeException(
                    "Requested page " + pageNumber + " exceeds total pages " + totalPages
            );
        }

        // Prepare result
        PaginationResultDto result = new PaginationResultDto();
        result.setItems(items);
        result.setPageNumber(pageNumber);
        result.setPageSize(pageSize);
        result.setTotalItems(totalItems);
        result.setTotalPages(totalPages);

        return result;








    }
}
