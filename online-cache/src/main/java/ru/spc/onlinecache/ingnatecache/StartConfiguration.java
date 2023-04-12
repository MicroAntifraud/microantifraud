package ru.spc.onlinecache.ingnatecache;

import lombok.AllArgsConstructor;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import ru.spc.requesthandler.Transaction;

import java.util.HashMap;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StartConfiguration {
    private final Ignite ignite;
    private final LoadToggle loadToggle;

    @EventListener(ApplicationReadyEvent.class)
    public void loadCache () {
        if (loadToggle.isLoad()) {
            Map<Long, Transaction> fromPersist = new HashMap<>();
            IgniteCache<Long, Transaction> cache = ignite.cache("transaction");
            cache.loadCache((key, value) -> {
                fromPersist.put(key, value);
                return true;
            }, null);
            cache.putAll(fromPersist);
        }
    }
}
