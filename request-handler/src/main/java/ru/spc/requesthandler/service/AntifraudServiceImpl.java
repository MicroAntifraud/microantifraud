package ru.spc.requesthandler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.enumeration.Verdict;
import ru.spc.requesthandler.mapper.TransactionMapper;
import ru.spc.requesthandler.model.Transaction;

@Service
public class AntifraudServiceImpl implements AntifraudService {
    @Autowired
    private TransactionMapper mapper;

    @Override
    public ResponseDto saveTransaction(Transaction request) {
        ResponseDto responseDto = mapper.toDto(request);
        if (true) {
            responseDto.setVerdict(Verdict.valueOf("ALLOW"));
        } else {
            responseDto.setVerdict(Verdict.valueOf("DENY"));
        }
        return responseDto;
    }

    public String getVerdict(Transaction transaction) {

        return "";
    }
}
