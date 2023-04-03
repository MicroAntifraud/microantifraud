package ru.spc.requesthandler.service;

import org.springframework.stereotype.Service;
import ru.spc.requesthandler.dto.RequestDto;
import ru.spc.requesthandler.dto.ResponseDto;

@Service
public interface TransactionService {
    ResponseDto saveTransaction(RequestDto request);
}
