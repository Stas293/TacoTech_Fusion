package com.spring.tacospring.data;

import com.spring.tacospring.model.TacoOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TacoOrderRepository extends JpaRepository<TacoOrder, Long> {
    @Query("SELECT o FROM TacoOrder o " +
            "JOIN FETCH o.tacos t " +
            "WHERE o.id = ?1 AND o.user.id = ?2")
    Optional<TacoOrder> findCompleteTacoOrderById(Long id, Long userId);

    Page<TacoOrder> findAllByUser_IdOrderByPlacedAtDesc(Pageable pageable, Long userId);

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, LocalDateTime start, LocalDateTime end);
}
