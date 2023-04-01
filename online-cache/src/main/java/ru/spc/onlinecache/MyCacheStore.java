package ru.spc.onlinecache;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.util.Collection;
import java.util.Map;

@Slf4j
public class MyCacheStore implements CacheStore<Integer, String> {
    public void loadCache(IgniteBiInClosure<Integer, String> igniteBiInClosure, @Nullable Object... objects) throws CacheLoaderException {

    }

    public void sessionEnd(boolean b) throws CacheWriterException {

    }

    public String load(Integer integer) throws CacheLoaderException {
        return null;
    }

    public Map<Integer, String> loadAll(Iterable<? extends Integer> iterable) throws CacheLoaderException {
        return null;
    }

    public void write(Cache.Entry<? extends Integer, ? extends String> entry) throws CacheWriterException {
        log.info(entry.getKey() + "=========" + entry.getValue());
    }

    public void writeAll(Collection<Cache.Entry<? extends Integer, ? extends String>> collection) throws CacheWriterException {

    }

    public void delete(Object o) throws CacheWriterException {

    }

    public void deleteAll(Collection<?> collection) throws CacheWriterException {

    }
}
