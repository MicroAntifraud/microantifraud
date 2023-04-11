package ru.spc.requesthandler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.spc.requesthandler.dto.ResponseDto;
import ru.spc.requesthandler.model.Transaction;
import ru.spc.requesthandler.service.AntifraudService;

@RestController
@RequestMapping("/api/v1/")
public class AntiFraudController {
    private final AntifraudService service;

    public AntiFraudController(AntifraudService service) {
        this.service = service;
    }

    @PostMapping("antifraud")
    public ResponseEntity<ResponseDto> checkTransaction(@RequestBody Transaction transaction) {
        ResponseDto verdict = service.getVerdict(transaction);
        service.saveTransaction(transaction);
        return new ResponseEntity<>(verdict, HttpStatus.OK);
    }
}
