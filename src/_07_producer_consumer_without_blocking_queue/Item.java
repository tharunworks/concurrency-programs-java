package _07_producer_consumer_without_blocking_queue;

import java.util.Random;

public class Item {
    public final int itemId;

    public Item() {
        this.itemId = new Random().nextInt();
    }
}
