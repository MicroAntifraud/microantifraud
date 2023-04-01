package ru.spc.onlinecache;


import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.expiry.Duration;
import javax.cache.expiry.ModifiedExpiryPolicy;
import java.util.concurrent.TimeUnit;

@Configuration
public class AntifraudConfiguration {

    @Bean
    public Ignite ignite() {
        CacheConfiguration<Integer, String> cacheConfiguration = new CacheConfiguration<>();
        cacheConfiguration.setCacheStoreFactory(MyCacheStore::new);
        cacheConfiguration.setName("myCache");
        cacheConfiguration.setWriteThrough(true);
        cacheConfiguration.setExpiryPolicyFactory(ModifiedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 10)));
        IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        igniteConfiguration.setPeerClassLoadingEnabled(true);
        igniteConfiguration.setDiscoverySpi(new TcpDiscoverySpi().setLocalAddress("127.0.0.1"));
        igniteConfiguration.setCacheConfiguration(cacheConfiguration);

        return Ignition.start(igniteConfiguration);
    }
}
