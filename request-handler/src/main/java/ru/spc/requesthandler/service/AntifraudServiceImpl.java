package ru.spc.requesthandler.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.springframework.stereotype.Service;
import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.enumeration.Verdict;
import ru.spc.requesthandler.mapper.TransactionMapper;
import ru.spc.requesthandler.Transaction;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class AntifraudServiceImpl implements AntifraudService {
    private final Integer LIMIT = 500;
    private final static String CACHE = "transaction";
    private final static String QUERY = "SELECT * FROM Transaction WHERE userId = ?";
    private final IgniteClient igniteClient;
    private final TransactionMapper mapper;

    public AntifraudServiceImpl(IgniteClient igniteClient, TransactionMapper mapper) {
        this.igniteClient = igniteClient;
        this.mapper = mapper;
    }

    public ResponseDto getVerdict(Transaction transaction) {
        ResponseDto responseDto = mapper.toDto(transaction);
        responseDto.setVerdict(Verdict.ALLOW);
        AtomicInteger sum = new AtomicInteger(0);

        ClientCache<Long, String> cache = igniteClient.cache(CACHE);
        SqlFieldsQuery sql = new SqlFieldsQuery(QUERY).setArgs(transaction.getUserId());

        try(QueryCursor<List<?>> cursor = cache.query(sql)) {
            for (List<?> row : cursor) {
                sum.set(sum.get() + (Integer)row.get(2));
            }

        }

        if (sum.get() + transaction.getAmount() > LIMIT) {
            responseDto.setVerdict(Verdict.DENY);
        }

        return responseDto;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        ClientCache<Long, Transaction> cache = igniteClient.cache(CACHE);
        cache.putAsync(transaction.getRequestId(), transaction);
        log.info("Транзакция отправлена в кэш: " + transaction);
    }
}
