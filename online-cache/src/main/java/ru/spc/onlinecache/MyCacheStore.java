package ru.spc.onlinecache;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;
import org.springframework.kafka.core.KafkaTemplate;
import ru.spc.requesthandler.Transaction;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class MyCacheStore implements CacheStore<Long, Transaction> {
    private final KafkaTemplate<Long, Transaction> template;

    public MyCacheStore(KafkaTemplate<Long, Transaction> template) {
        this.template = template;
    }

    public void loadCache(IgniteBiInClosure<Long, Transaction> igniteBiInClosure, @Nullable Object... objects) throws CacheLoaderException {

    }

    public void sessionEnd(boolean b) throws CacheWriterException {

    }

    public Transaction load(Long integer) throws CacheLoaderException {
        return null;
    }

    public Map<Long, Transaction> loadAll(Iterable<? extends Long> iterable) throws CacheLoaderException {
        return null;
    }

    public void write(Cache.Entry<? extends Long, ? extends Transaction> entry) throws CacheWriterException {
        log.info(entry.getKey() + "=========" + entry.getValue());
    }

    public void writeAll(Collection<Cache.Entry<? extends Long, ? extends Transaction>> collection) throws CacheWriterException {

    }

    public void delete(Object o) throws CacheWriterException {

    }

    public void deleteAll(Collection<?> collection) throws CacheWriterException {

    }
}
