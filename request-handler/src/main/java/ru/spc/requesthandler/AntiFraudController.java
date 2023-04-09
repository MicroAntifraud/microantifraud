package ru.spc.requesthandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class AntiFraudController {
    private AntifraudService service;

    public AntiFraudController(AntifraudService service) {
        this.service = service;
    }

    @PostMapping("antifraud")
    public ResponseEntity<String> checkTransaction(@RequestBody Transaction transaction) {
        String verdict = service.getVerdict(transaction);
        if (verdict == null) {
            return new ResponseEntity<>("lol", HttpStatus.OK);
        }
        service.saveTransaction(transaction);

        return new ResponseEntity<>(verdict, HttpStatus.OK);
    }
}
