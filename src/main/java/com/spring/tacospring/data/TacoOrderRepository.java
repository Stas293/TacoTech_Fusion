package com.spring.tacospring.data;

import com.spring.tacospring.model.TacoOrder;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TacoOrderRepository extends CassandraRepository<TacoOrder, UUID> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, LocalDateTime start, LocalDateTime end);
}
