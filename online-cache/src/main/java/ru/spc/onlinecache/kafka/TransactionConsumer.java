package ru.spc.onlinecache.kafka;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionConsumer {
    @KafkaListener(topics = "${topicName}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        log.info("=> consumed {}", message);
    }
}
