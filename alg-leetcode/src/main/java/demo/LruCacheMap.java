package demo;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LRU-K 缓存算法,最近请求数量达到2次会进行缓存,优先读取 `map` 里面的缓存热点数据
 * LinkedHashMap 会自动删除最久未访问数据
 *
 * @author chenpeng
 * @date 2018/2/7
 */
public class LruCacheMap<K, V> {
    private static final float HASH_LOAD_FACTORY = 0.75f;
    /**
     * 对象缓存队列
     */
    private LinkedHashMap<K, V> map;
    /**
     * 访问次数记录队列,长度与对象缓存队列一致,
     */
    private LinkedHashMap<K, Integer> visitMap;
    private int cacheSize;
    private static final Integer CACHE_COUNT = 2;

    public LruCacheMap(int cacheSize) {
        this.cacheSize = cacheSize;
        int capacity = (int) Math.ceil(cacheSize / HASH_LOAD_FACTORY) + 1;
        map = new LinkedHashMap<K, V>(capacity, HASH_LOAD_FACTORY, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LruCacheMap.this.cacheSize;
            }
        };

        visitMap = new LinkedHashMap<K, Integer>(capacity, HASH_LOAD_FACTORY, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LruCacheMap.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
         return map.get(key);
    }

    public synchronized void put(K key, V value) {
        Integer visitCount = visitMap.get(key);
        if (visitCount != null && visitCount >= (CACHE_COUNT - 1)) {
            map.put(key, value);
            return;
        }
        visitMap.put(key, visitCount == null ? 1 : (visitCount + 1));
    }

    public synchronized void clear() {
        visitMap.clear();
        map.clear();
    }

    public synchronized int usedSize() {
        return map.size();
    }

    public void print() {
        System.out.println("----entry----");
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "-----" + entry.getValue());
        }
        System.out.println("----visit entry----");
        for (Map.Entry<K, Integer> entry : visitMap.entrySet()) {
            System.out.println(entry.getKey() + "-----" + entry.getValue());
        }
    }
}