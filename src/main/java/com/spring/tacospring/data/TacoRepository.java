package com.spring.tacospring.data;

import com.spring.tacospring.model.Taco;

public interface TacoRepository {
    long save(Long orderId, int orderKey, Taco taco);
}
