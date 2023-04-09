package ru.spc.requesthandler;

import org.apache.ignite.cache.query.SqlQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.springframework.stereotype.Service;

@Service
public class AntifraudService {
    private final static String QUERY = "SELECT * FROM Transaction WHERE userId = ?";
    private final IgniteClient igniteClient;

    public AntifraudService(IgniteClient igniteClient) {
        this.igniteClient = igniteClient;
    }

    public String getVerdict(int id) {
        SqlQuery<String, Transaction> sqlQuery = new SqlQuery<String, Transaction>(Transaction.class, QUERY)
                .setArgs(id);
        ClientCache<Integer, String> cache = igniteClient.cache("myCache");
        //cache.query(sqlQuery).getAll().stream().map(transaction -> transaction.getValue().amount).
        return cache.get(id);
    }

    public void saveTransaction(int id, String transaction) {
        ClientCache<Integer, String> cache = igniteClient.cache("myCache");
        cache.putAsync(id, transaction);
    }
}
