package ru.spc.onlinecache.repo;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.spc.requesthandler.Transaction;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public class MyRepo implements Serializable {
    private final PGSimpleDataSource transactionRepo;

    public MyRepo(@Qualifier("myJdbc") PGSimpleDataSource transactionRepo) throws SQLException {
        this.transactionRepo = transactionRepo;
        transactionRepo.getConnection();
    }

    public List<Transaction> findAll() throws SQLException {
        List<Transaction> result = null;
        try {
        result = getTemplate().query("SELECT * FROM transaction", (rs, rowNum) -> {
            Transaction transaction = new Transaction();
            transaction.setRequestId(rs.getLong(1));
            transaction.setUserId(rs.getInt(2));
            transaction.setAmount(rs.getInt(3));
            return transaction;
        });

        } catch (Exception e) {
           log.error("Не удалось получить данные из базы, возможно она пустая - {}", e);
        }
        return result;
    }

    public void save(Long id, Transaction transaction) throws SQLException {
        getTemplate().update("DELETE FROM transaction WHERE id=?", id);
        getTemplate().update("INSERT INTO transaction VALUES (?, ?, ?)", id, transaction.getUserId(), transaction.getAmount());
    }

    private JdbcTemplate getTemplate() throws SQLException {
        Connection connection = transactionRepo.getConnection();
        connection.setAutoCommit(true);
        JdbcTemplate template = new JdbcTemplate(transactionRepo);
        return template;
    }
}
