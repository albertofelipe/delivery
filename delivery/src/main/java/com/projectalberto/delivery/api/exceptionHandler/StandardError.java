package com.projectalberto.delivery.api.exceptionHandler;

import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

public record StandardError(Integer status,
                            OffsetDateTime moment,
                            String message,
                            List<FieldsBody> fields) {
}