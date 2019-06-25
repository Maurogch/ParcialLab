package edu.utn.parcialLaboratorioV.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AppConfiguration {
    @Value("${executor.corePoolSize: 10}")
    private Integer CORE_POOL_SIZE;
    @Value("${executor.maxPoolSize: 50}")
    private Integer MAX_POOL_SIZE;
    @Value("${executor.queueCapacity: 500}")
    private Integer QUEUE_CAPACITY;

    @Bean("Executor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.initialize();

        return executor;
    }
}
