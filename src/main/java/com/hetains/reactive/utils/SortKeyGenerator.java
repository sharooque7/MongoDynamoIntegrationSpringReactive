package com.hetains.reactive.utils;

import java.time.Instant;
import java.util.UUID;

public  class  SortKeyGenerator {

    public static String generateSortKey() {
        // Generate a unique ID using a combination of timestamp and random UUID
        String timestamp = Instant.now().toString();
        String uuid = UUID.randomUUID().toString();
        return timestamp + "_" + uuid;
    }
}
