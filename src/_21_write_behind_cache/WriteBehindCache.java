package _21_write_behind_cache;

import java.util.*;
import java.util.concurrent.*;

public class WriteBehindCache<K, V> {
	private final Map<K, V> cache = new ConcurrentHashMap<>();
	private final Queue<CacheEntry<K, V>> writeQueue = new ConcurrentLinkedQueue<>();
	private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	public WriteBehindCache() {
		// Flush writes every 5 seconds
		executor.scheduleAtFixedRate(this::flushWrites, 5, 5, TimeUnit.SECONDS);
	}

	public void put(K key, V value) {
		cache.put(key, value);
		writeQueue.offer(new CacheEntry<>(key, value));
	}

	public V get(K key) {
		return cache.get(key); // Reads always hit cache for strong consistency
	}

	private void flushWrites() {
		CacheEntry<K, V> entry;
		System.out.println("Flushing key-value pairs");
		while ((entry = writeQueue.poll()) != null) {
			persist(entry);
		}
	}

	private void persist(CacheEntry<K, V> entry) {
		System.out.println("key: " + entry.key + ", value: " + entry.value);
		// Persist entry to persistent storage
		// e.g., Database or Disk write
	}

	public void shutDown() throws InterruptedException {
		if (executor != null) {
			// Compliant: attempts graceful shutdown before doing so forcefully.
			executor.shutdown();
			if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
				throw new IllegalStateException("Timed out while waiting for executing threads to terminate");
			}
			executor.shutdownNow();
		}
	}
	static class CacheEntry<K, V> {
		K key;
		V value;

		CacheEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}
