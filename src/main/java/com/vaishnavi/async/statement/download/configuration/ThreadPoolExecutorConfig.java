package com.vaishnavi.async.statement.download.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class contains configuration for executor pool
 *
 * @author Vaishnavi Bagal
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class ThreadPoolExecutorConfig {
    /**
     * To create fix thread pool executor based on no of cores
     *
     * @return ExecutorService
     */
    @Bean
    public ExecutorService createThreadPoolExecutor() {
        int cores = Runtime.getRuntime().availableProcessors();
        return Executors.newFixedThreadPool(cores - 2);
    }
}
