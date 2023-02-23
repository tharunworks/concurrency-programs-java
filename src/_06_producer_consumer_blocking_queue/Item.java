package _06_producer_consumer_blocking_queue;

import java.util.Random;
import java.util.UUID;

public class Item {
    public final int itemId;

    public Item() {
        this.itemId = new Random().nextInt();
    }
}
