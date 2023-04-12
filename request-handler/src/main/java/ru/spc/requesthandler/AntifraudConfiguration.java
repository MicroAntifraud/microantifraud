package ru.spc.requesthandler;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AntifraudConfiguration {
    @Bean
    public IgniteClient igniteClient() {
       ClientConfiguration cfg = new ClientConfiguration().setAddresses("online:10800");
       //ClientConfiguration cfg = new ClientConfiguration().setAddresses("localhost:10800");
        return Ignition.startClient(cfg);
    }
}
