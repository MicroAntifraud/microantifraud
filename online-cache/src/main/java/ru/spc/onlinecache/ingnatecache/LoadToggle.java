package ru.spc.onlinecache.ingnatecache;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class LoadToggle {
    @Getter
    @Setter
    private boolean load;
}
