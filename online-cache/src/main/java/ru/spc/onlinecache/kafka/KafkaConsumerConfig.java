package ru.spc.onlinecache.kafka;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;
import ru.spc.onlinecache.requesthandler.Transaction;

import java.util.HashMap;
import java.util.Map;
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    String kafkaServer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    static final Integer CONCURRENCY = 5;

    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, Transaction> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(CONCURRENCY);
        return factory;
    }

    private ConsumerFactory<Long, Transaction> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }
}
