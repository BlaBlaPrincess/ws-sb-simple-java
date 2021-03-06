package com.blablaprincess.springboot_simplejava.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class AsyncConfig {

    @Bean(name = "telegramBotNotifierServiceTaskExecutor")
    public Executor telegramBotNotifierServiceTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("tg-notifier-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "controllerCallsHistoryServiceTaskExecutor")
    public Executor controllerCallsHistoryServiceTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("cch-service-");
        executor.initialize();
        return executor;
    }

}
