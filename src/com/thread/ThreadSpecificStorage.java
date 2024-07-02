package com.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class ThreadSpecificStorage {

    /**
     * Java 標準 API 就提供了 java.lang.ThreadLocal 可以使用
     */
    public static void main(String[] args) {

        ThreadScope.get().put("connectionId", 1);

        System.out.println(Thread.currentThread().getName() + ":" + ThreadScope.get().get("connectionId"));

        new Thread(() -> {
            ThreadScope.get().put("connectionId", 2);
            System.out.println(Thread.currentThread().getName() + ":" + ThreadScope.get().get("connectionId"));
        }).start();
    }
}

/**
 * 在 Thread 生命週期結束，被 JVM 回收之後，對應的資源也應當被移除，
 * 因此這邊使用了 WeakHashMap，作為 Thread 的鍵被回收後，對應的鍵／值也會從 WeakHashMap 移除。
 */
class ThreadLocal<T> {
    private Map<Thread, T> storage = Collections.synchronizedMap(new WeakHashMap<>());

    T get() {
        return storage.get(Thread.currentThread());
    }

    void set(T value) {
        storage.put(Thread.currentThread(), value);
    }

    void remove() {
        storage.remove(Thread.currentThread());
    }

}

/**
 * 執行緒／資源
 *  Thread-Specific Storage 就是以執行緒為單位，儲存對應的資源，
 *  方才只是以名稱空間為例，實際上是為每個執行緒儲存對應的 HashMap。
 */
class ThreadScope {
    private static final ThreadLocal<Map<String, Object>> scopes = new ThreadLocal<>();

    static Map<String, Object> get() {
        var scope = scopes.get();

        if(scope == null) {
            System.out.println("scope is empty, thread scope will set value");
            scope = new HashMap<>();
            scopes.set(scope);
        }

        return scope;
    }
}

/**
 *  這類應用也可以用來做資源快取
 */
class Resource {
    private static final ThreadLocal<Resource> resources = new ThreadLocal<>();

    public static Resource getResource() {
        var resource = resources.get();

        if(resource == null) {
            resource = new Resource();
            resources.set(resource);
        }

        return resource;
    }
}