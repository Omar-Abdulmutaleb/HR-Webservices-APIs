package gov.iti.jets.persistence.utils;

import java.lang.reflect.Field;

public class EntityUpdateUtil {

    public static void copyNonNullProperties(Object src, Object target) {
        Class<?> srcClass = src.getClass();
        for (Field srcField : srcClass.getDeclaredFields()) {
            srcField.setAccessible(true);
            try {
                Object value = srcField.get(src);
                if (value != null) {
                    try {
                        Field targetField = target.getClass().getDeclaredField(srcField.getName());
                        targetField.setAccessible(true);
                        targetField.set(target, value);
                    } catch (NoSuchFieldException e) {
                        // Field does not exist in the target object, skip to the next field
                        continue;
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
