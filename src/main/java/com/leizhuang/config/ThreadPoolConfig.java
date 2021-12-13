package com.leizhuang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author LeiZhuang
 * @date 2021/10/28 19:35
 */
@Configuration
@EnableAsync//开启多线程
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor asyncServiceExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor() ;
//        核心线程数
        executor.setCorePoolSize(5);
//        最大线程数
        executor.setMaxPoolSize(20);
//        队列大小
        executor.setQueueCapacity(Integer.MAX_VALUE);
//        线程存活时间
        executor.setKeepAliveSeconds(60);
//        默认线程名称
        executor.setThreadNamePrefix("个人博客项目");
//        等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
//        执行初始化
        executor.initialize();
        return executor;

    }
}
