package ru.spc.onlinecache;


import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.QueryEntity;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import ru.spc.requesthandler.Transaction;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
public class AntifraudConfiguration {
    @Bean
    public KafkaTemplate<Long, Transaction> template() {
        return null;
    }

    @Bean
    public MyCacheStore cacheStore(KafkaTemplate<Long, Transaction> template) {
        return new MyCacheStore(template);
    }

    @Bean
    public MyCacheStoreFactory factory(MyCacheStore cacheStore) {
        return new MyCacheStoreFactory(cacheStore);
    }

    @Bean
    public Ignite ignite(MyCacheStoreFactory factory) {
        CacheConfiguration<Long, Transaction> cacheConfiguration = new CacheConfiguration<>();
        cacheConfiguration.setCacheStoreFactory(factory);
        cacheConfiguration.setName("transaction");
        QueryEntity queryEntity = new QueryEntity(Long.class, Transaction.class)
                .addQueryField("requesId", Long.class.getName(), null)
                .addQueryField("userId", Integer.class.getName(), null)
                .addQueryField("amount", Integer.class.getName(), null);
        cacheConfiguration.setQueryEntities(Arrays.asList(queryEntity));
        cacheConfiguration.setWriteThrough(true);
        cacheConfiguration.setReadThrough(true);
        cacheConfiguration.setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 60)));
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setDiscoverySpi(new TcpDiscoverySpi().setLocalAddress("127.0.0.1"));
        igniteConfiguration.setWorkDirectory("/tmp");
        igniteConfiguration.setCacheConfiguration(cacheConfiguration);

        return Ignition.start(igniteConfiguration);
    }
}
