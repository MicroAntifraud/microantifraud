package ru.spc.onlinecache.ingnatecache;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.ignite.cache.store.CacheStore;
import org.springframework.stereotype.Service;
import ru.spc.onlinecache.requesthandler.Transaction;

import javax.cache.configuration.Factory;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyCacheStoreFactory implements Factory<CacheStore<Long, Transaction>> {

    @Override
    public CacheStore<Long, Transaction> create() {
        return new MyCacheStore();
    }
}
