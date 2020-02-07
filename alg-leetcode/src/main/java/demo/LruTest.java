package demo;

public class LruTest {
    public static void main(String[] args) {
        LruCacheMap lruCacheMap = new LruCacheMap(3);
        lruCacheMap.put("111", "111");
        lruCacheMap.put("222", "222");
        lruCacheMap.put("333", "333");
        lruCacheMap.put("444", "444");
        lruCacheMap.print();
        System.err.println("&&&&&&&&&&&&&&&&");
        lruCacheMap.get("222");
        lruCacheMap.get("222");
        lruCacheMap.get("222");
        lruCacheMap.print();
        System.err.println("&&&&&&&&&&&&&&&&");
        lruCacheMap.get("333");
        lruCacheMap.get("333");
        lruCacheMap.get("444");
        lruCacheMap.get("444");
        lruCacheMap.print();
        System.err.println("&&&&&&&&&&&&&&&&");
        lruCacheMap.put("111", "111");
        lruCacheMap.get("111");
        lruCacheMap.get("111");
        lruCacheMap.get("111");
        lruCacheMap.print();
    }
}
