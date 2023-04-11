package ru.spc.onlinecache.repo;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.spc.onlinecache.requesthandler.Transaction;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
public class MyRepo implements Serializable {
    private final PGSimpleDataSource transactionRepo;

    public MyRepo(@Qualifier("myJdbc") PGSimpleDataSource transactionRepo) throws SQLException {
        this.transactionRepo = transactionRepo;
        transactionRepo.getConnection();
    }

    public List<Transaction> findAll() throws SQLException {

        getTemplate().queryForObject("SELECT * FROM transaction", Transaction.class);

        return Collections.emptyList();
    }

    public void save(Long id, Transaction transaction) throws SQLException {



        System.out.println("=========SAVE============" + transaction);
      //  template.update("INSERT INTO transaction VALUES (?, ?, ?, ?)", transaction.getRequestId(), transaction.getUserId());


    }

    private JdbcTemplate getTemplate() throws SQLException {
        Connection connection = transactionRepo.getConnection();
        connection.setAutoCommit(true);
        JdbcTemplate template = new JdbcTemplate(transactionRepo);
        return template;
    }
}
