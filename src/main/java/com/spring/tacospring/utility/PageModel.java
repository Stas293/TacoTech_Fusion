package com.spring.tacospring.utility;

import org.springframework.data.domain.Slice;

public record PageModel<T>(
        Slice<T> page,
        String nextPagingState
) {
}
