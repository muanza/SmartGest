package com.smartgest.testutil;

import java.lang.reflect.Field;

public final class ReflectionTestUtils {

    private ReflectionTestUtils() {
    }

    public static void setField(Object target, String fieldName, Object value) {
        try {
            Field field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (ReflectiveOperationException ex) {
            throw new IllegalStateException("Could not set field " + fieldName, ex);
        }
    }
}
