package ru.spc.requesthandler;

import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.springframework.stereotype.Service;

@Service
public class AntifraudService {
    private final IgniteClient igniteClient;

    public AntifraudService(IgniteClient igniteClient) {
        this.igniteClient = igniteClient;
    }

    public String getVerdict(int id) {
        ClientCache<Integer, String> cache = igniteClient.cache("myCache");
        return cache.get(id);
    }

    public void saveTransaction(int id, String transaction) {
        ClientCache<Integer, String> cache = igniteClient.cache("myCache");
        cache.putAsync(id, transaction);
    }
}
