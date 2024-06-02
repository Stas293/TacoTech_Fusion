package com.spring.tacospring.service;

import com.datastax.oss.protocol.internal.util.Bytes;
import com.spring.tacospring.data.TacoOrderRepository;
import com.spring.tacospring.dto.BaseOrderReadDTO;
import com.spring.tacospring.dto.OrderReadDTO;
import com.spring.tacospring.mapper.BaseTacoOrderMapper;
import com.spring.tacospring.mapper.TacoOrderMapper;
import com.spring.tacospring.model.TacoOrder;
import com.spring.tacospring.utility.PageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TacoOrderService {
    private final TacoOrderRepository tacoOrderRepository;
    private final BaseTacoOrderMapper tacoOrderMapper;
    private final TacoOrderMapper orderMapper;

    @Transactional
    public TacoOrder save(TacoOrder order) {
        return tacoOrderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public PageModel<BaseOrderReadDTO> findAll(Pageable pageable, String pagingState) {
        Optional<ByteBuffer> byteBufferOptional = Optional.ofNullable(pagingState)
                .map(Bytes::fromHexString);
        CassandraPageRequest cassandraPageable = CassandraPageRequest.of(pageable,
                byteBufferOptional.orElse(null));
        Slice<BaseOrderReadDTO> page = tacoOrderRepository.findAll(cassandraPageable)
                .map(tacoOrderMapper::toDto);
        Optional<String> nextPagingState = Optional.of(page)
                .filter(Slice::hasNext)
                .map(p -> ((CassandraPageRequest) p.getPageable()).getPagingState())
                .map(Bytes::toHexString);
        return new PageModel<>(page, nextPagingState.orElse(null));
    }

    @Transactional(readOnly = true)
    public Optional<OrderReadDTO> findById(String id) {
        return tacoOrderRepository.findById(UUID.fromString(id))
                .map(orderMapper::toDto);
    }
}