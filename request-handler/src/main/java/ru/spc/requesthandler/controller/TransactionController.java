package ru.spc.requesthandler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.spc.requesthandler.dto.RequestDto;
import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.service.TransactionService;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto save(@RequestBody RequestDto request) {
        return transactionService.saveTransaction(request);
    }

//    @PostMapping("/transaction")
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public Mono<ResponseDto> saveAsync(@RequestBody RequestDto request) {
//        return Mono.just(transactionService.saveTransaction(request));
//    }
}
