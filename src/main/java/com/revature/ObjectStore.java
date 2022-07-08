package com.revature;

import java.util.HashMap;
import java.util.Map;

public class ObjectStore {
    private static Map<String, Object> store = new HashMap<>();

    public static void add(String key, Object object) {
        store.put(key, object);
    }

    public static Object get(String key) {
        return store.get(key);
    }

    public static void remove(String key) {
        store.remove(key);
    }



}
