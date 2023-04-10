package ru.spc.onlinecache.ingnatecache;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import ru.spc.onlinecache.kafka.KafkaTopicConfig;
import ru.spc.onlinecache.requesthandler.Transaction;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.Collection;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
@RequiredArgsConstructor
public class MyCacheStore implements CacheStore<Long, Transaction> {
    @Autowired
    KafkaTemplate<Long, Transaction> template;
    @Autowired
    KafkaTopicConfig topicConfig;


    @Override
    public void loadCache(IgniteBiInClosure<Long, Transaction> igniteBiInClosure, @Nullable Object... objects) throws CacheLoaderException {

    }

    @Override
    public void sessionEnd(boolean b) throws CacheWriterException {

    }

    @Override
    public Transaction load(Long key) throws CacheLoaderException {
        return null;
    }

    @Override
    public Map<Long, Transaction> loadAll(Iterable<? extends Long> keys) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends Transaction> entry) throws CacheWriterException {
        log.info("Receive and write cache message with key - "
                + entry.getKey() + " ================ " + " value " + entry.getValue());
        template.send(topicConfig.getTopic().name(), entry.getKey(), entry.getValue());
        log.info("Send to topic " + topicConfig.getTopic().name() +
                " - message with key - " + entry.getKey() + " and value - " + entry.getValue());
    }

    @Override
    public void writeAll(Collection<Cache.Entry<? extends Long, ? extends Transaction>> entries) throws CacheWriterException {

    }

    @Override
    public void delete(Object key) throws CacheWriterException {

    }

    @Override
    public void deleteAll(Collection<?> keys) throws CacheWriterException {

    }
}
