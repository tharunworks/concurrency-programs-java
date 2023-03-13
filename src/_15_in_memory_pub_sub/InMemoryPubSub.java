package _15_in_memory_pub_sub;

public interface InMemoryPubSub {
    public void put(String value);
    public String get(Integer offset);
}
