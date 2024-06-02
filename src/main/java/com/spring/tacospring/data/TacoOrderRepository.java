package com.spring.tacospring.data;

import com.spring.tacospring.model.TacoOrder;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TacoOrderRepository extends MongoRepository<TacoOrder, ObjectId> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, LocalDateTime start, LocalDateTime end);
}
