package com.vaishnavi.async.statement.download.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolExecutorConfig {

    @Bean
    public ExecutorService createThreadPoolExecutor(){
        int cores = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(cores-2);
    }
}
