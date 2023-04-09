package ru.spc.onlinecache;

import org.apache.ignite.cache.store.CacheStore;
import org.springframework.kafka.core.KafkaTemplate;
import ru.spc.requesthandler.Transaction;

import javax.cache.configuration.Factory;

public class MyCacheStoreFactory implements Factory<CacheStore<Long, Transaction>> {
    private final MyCacheStore cacheStore;

    public MyCacheStoreFactory(MyCacheStore cacheStore) {
        this.cacheStore = cacheStore;
    }

    @Override
    public CacheStore<Long, Transaction> create() {
        return this.cacheStore;
    }
}
