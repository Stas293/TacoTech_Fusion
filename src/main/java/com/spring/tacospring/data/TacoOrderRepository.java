package com.spring.tacospring.data;

import com.spring.tacospring.model.TacoOrder;

public interface TacoOrderRepository {
    TacoOrder save(TacoOrder order);
}
