package com.projectalberto.delivery.api.exceptionHandler;

import java.time.OffsetDateTime;

public record StandardError(
    Integer status,
    OffsetDateTime moment,
    String message
) {
}
