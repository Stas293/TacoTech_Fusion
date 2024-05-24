package com.spring.tacospring.data;

import com.spring.tacospring.model.TacoOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
    @Query("SELECT o FROM TacoOrder o " +
            "JOIN FETCH o.tacos t " +
            "WHERE o.id = ?1")
    Optional<TacoOrder> findCompleteTacoOrderById(Long id);

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, LocalDateTime start, LocalDateTime end);
}
