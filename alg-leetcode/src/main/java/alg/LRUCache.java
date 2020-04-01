package alg;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LRUCache {

    /**
     * 单例
     */
    private static LRUCache lruCache = new LRUCache();

    public static LRUCache getInstance() {
        return lruCache;
    }

    /**
     *  所占内存 大概10mb
      */
    private final int maxSize = 1000;
    private ConcurrentHashMap<String, String> map;
    private ConcurrentLinkedQueue<String> queue;

    /**
     * 不能外部实例化
     */
    private LRUCache() {
        this.map = new ConcurrentHashMap<>(maxSize);
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public void put(final String key, final String value) {
        if (map.containsKey(key)) {
            queue.remove(key);
        }
        if (queue.size() >= maxSize) {
            String oldKey = queue.poll();
            if (null != oldKey) {
                map.remove(oldKey);
            }
        }
        queue.add(key);
        map.put(key, value);
    }

    public String get(String key) {
        if (queue.remove(key)) {
            queue.add(key);
        }
        return map.get(key);
    }
}
