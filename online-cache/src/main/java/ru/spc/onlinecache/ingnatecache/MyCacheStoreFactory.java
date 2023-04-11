package ru.spc.onlinecache.ingnatecache;

import lombok.AllArgsConstructor;
import org.apache.ignite.cache.store.CacheStore;
import ru.spc.onlinecache.repo.MyRepo;
import ru.spc.onlinecache.requesthandler.Transaction;

import javax.cache.configuration.Factory;

@AllArgsConstructor
public class MyCacheStoreFactory implements Factory<CacheStore<Long, Transaction>> {
    private final MyRepo repo;

    @Override
    public CacheStore<Long, Transaction> create() {
        return new MyCacheStore(repo);
    }
}
