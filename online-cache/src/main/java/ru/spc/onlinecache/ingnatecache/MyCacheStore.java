package ru.spc.onlinecache.ingnatecache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.spc.onlinecache.repo.MyRepo;
import ru.spc.onlinecache.requesthandler.Transaction;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
public class MyCacheStore implements CacheStore<Long, Transaction> {
    private final MyRepo repo;


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
        System.out.println("================LOAD=============");
        Map<Long, Transaction> map = new HashMap<>();
        try {
            repo.findAll().forEach(tr -> map.put(tr.getRequestId(), tr));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends Transaction> entry) throws CacheWriterException {

        System.out.println("===============WRITTE===========");
        log.info("Receive and write cache message with key - "
                + entry.getKey() + " ================ " + " value " + entry.getValue());
        try {
            repo.save(entry.getKey(), entry.getValue());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
