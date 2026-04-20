package edu.classproject.common;

import java.util.UUID;

public final class IdGenerator {
    private IdGenerator() {
    }

    public static String nextId(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8);
    }
}
