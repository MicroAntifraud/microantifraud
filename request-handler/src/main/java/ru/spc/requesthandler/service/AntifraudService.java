package ru.spc.requesthandler.service;

import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.model.Transaction;

public interface AntifraudService {
    ResponseDto saveTransaction(Transaction request);
    String getVerdict(Transaction transaction);
}
