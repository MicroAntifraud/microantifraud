package ru.spc.requesthandler;

import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class AntifraudService {
    private final Integer LIMIT = 500;
    private final static String ALLOW_VERDICT = "ALLOW";
    private final static String DENY_VERDICT = "DENY";
    private final static String CACHE = "transaction";
    private final static String QUERY = "SELECT * FROM Transaction WHERE userId = ?";
    private final IgniteClient igniteClient;

    public AntifraudService(IgniteClient igniteClient) {
        this.igniteClient = igniteClient;
    }

    public String getVerdict(Transaction transaction) {
        String verdict = ALLOW_VERDICT;
        ClientCache<Long, String> cache = igniteClient.cache(CACHE);
        AtomicInteger sum = new AtomicInteger(0);

        SqlFieldsQuery sql = new SqlFieldsQuery(QUERY).setArgs(transaction.userId);

        try(QueryCursor<List<?>> cursor = cache.query(sql)) {
            for (List<?> row : cursor) {
                sum.set(sum.get() + (Integer)row.get(2));
            }

        }

        if (sum.get() + transaction.amount > LIMIT) {
            verdict = DENY_VERDICT;
        }
        return verdict;
    }

    public void saveTransaction(Transaction transaction) {
        ClientCache<Long, Transaction> cache = igniteClient.cache(CACHE);
        cache.putAsync(transaction.requestId, transaction);
    }
}
