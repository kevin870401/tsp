package com.tsp;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.HttpStatus.OK;

public class ControllerUtils {

    @SuppressWarnings("PMD.ShortMethodName")
    public static <T> ResponseEntity<?> ok (String key, T value) {
        return new ResponseEntity(ImmutableMap.of(key, value), OK);
    }
}