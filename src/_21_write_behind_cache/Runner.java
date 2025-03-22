package _21_write_behind_cache;

public class Runner {
    void init(){
        WriteBehindCache<String, String> writeBehindCache = new WriteBehindCache<>();
        System.out.println("Started inserting key-value pairs");
        writeBehindCache.put("key1", "value1");
        writeBehindCache.put("key2", "value2");
        try {
            Thread.sleep(10000); // sleep call to simulate periodic flushes
            System.out.println("Inserting key-value pairs");
            writeBehindCache.put("key3", "value3");
            writeBehindCache.put("key4", "value4");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
