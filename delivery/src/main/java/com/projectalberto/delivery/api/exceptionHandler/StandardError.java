package com.projectalberto.delivery.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StandardError(Integer status,
                            OffsetDateTime moment,
                            String message,
                            List<FieldsBody> fields) {
}