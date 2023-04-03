package ru.spc.requesthandler.service;

//import org.apache.ignite.client.ClientCache;
//import org.apache.ignite.client.IgniteClient;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spc.requesthandler.dto.RequestDto;
import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.enumeration.Verdict;
import ru.spc.requesthandler.mapper.TransactionMapper;
import ru.spc.requesthandler.model.Transaction;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper mapper;

    @Override
    public ResponseDto saveTransaction(RequestDto request) {
        Transaction transaction = mapper.toEntity(request);
        ResponseDto responseDto = mapper.toDto(transaction);
        if (true) {
            responseDto.setVerdict(Verdict.valueOf("ALLOW"));
        } else {
            responseDto.setVerdict(Verdict.valueOf("DENY"));
        }
        return responseDto;
    }
}
