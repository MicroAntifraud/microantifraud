package ru.spc.requesthandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/")
public class AntiFraudController {
    private AntifraudService service;

    public AntiFraudController(AntifraudService service) {
        this.service = service;
    }

    @GetMapping("antifraud/{id}")
    public String getSomething(@PathVariable int id) {
        String verdict = service.getVerdict(id);

        if (verdict == null) {
            verdict = "lol";
        }
        service.saveTransaction(id, id + "Goga");
        return verdict;
    }
}
