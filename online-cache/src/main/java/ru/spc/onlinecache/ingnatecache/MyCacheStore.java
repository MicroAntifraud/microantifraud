package ru.spc.onlinecache.ingnatecache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.store.CacheStore;
import org.apache.ignite.lang.IgniteBiInClosure;
import org.jetbrains.annotations.Nullable;
import ru.spc.onlinecache.repo.MyRepo;
import ru.spc.requesthandler.Transaction;

import javax.cache.Cache;
import javax.cache.integration.CacheLoaderException;
import javax.cache.integration.CacheWriterException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
public class MyCacheStore implements CacheStore<Long, Transaction> {
    private final MyRepo repo;


    @Override
    public void loadCache(IgniteBiInClosure<Long, Transaction> igniteBiInClosure, @Nullable Object... objects) throws CacheLoaderException {
        log.info("Загружаем данные из базы в кэш: ");
        try {
            Objects.requireNonNull(repo.findAll(), "Из базы ничего не получили").forEach(tr -> {
                log.info("Запись из базы: " + tr.getRequestId()  + " : " + tr);
                igniteBiInClosure.apply(tr.getRequestId(), tr);
                    }
            );

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sessionEnd(boolean b) throws CacheWriterException {

    }

    @Override
    public Transaction load(Long key) throws CacheLoaderException {
        return null;
    }

    @Override
    public Map<Long, Transaction> loadAll(Iterable<? extends Long> iterable) throws CacheLoaderException {
        return null;
    }

    @Override
    public void write(Cache.Entry<? extends Long, ? extends Transaction> entry) throws CacheWriterException {
        log.info("Записали сообщение в кэш, отправляем базу данных: "
                + entry.getKey() + " : " + entry.getValue());
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
