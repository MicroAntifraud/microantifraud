package ru.spc.requesthandler.service;

import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.Transaction;

public interface AntifraudService {
    ResponseDto getVerdict(Transaction transaction);
    void saveTransaction(Transaction request);
}
