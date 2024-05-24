package com.spring.tacospring.data;

import com.spring.tacospring.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
}
