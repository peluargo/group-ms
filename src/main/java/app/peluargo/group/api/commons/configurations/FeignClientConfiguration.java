package app.peluargo.group.api.commons.configurations;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
    @Bean
    Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }
}
