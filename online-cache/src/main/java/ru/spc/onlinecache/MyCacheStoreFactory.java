package ru.spc.onlinecache;

import org.apache.ignite.cache.store.CacheStore;
import org.springframework.kafka.core.KafkaTemplate;

import javax.cache.configuration.Factory;

public class MyCacheStoreFactory implements Factory<CacheStore<Integer, String>> {
    private KafkaTemplate<Integer, String> template;

    public MyCacheStoreFactory(KafkaTemplate<Integer, String> template) {
        this.template = template;
    }

    @Override
    public CacheStore<Integer, String> create() {



        return null;
    }
}
