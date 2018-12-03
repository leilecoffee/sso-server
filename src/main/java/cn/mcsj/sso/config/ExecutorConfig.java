package cn.mcsj.sso.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class ExecutorConfig {

	@Value("${spring.executor.corePoolSize}")
	private int corePoolSize;

	@Value("${spring.executor.maxPoolSize}")
	private int maxPoolSize;

	@Value("${spring.executor.queueCapacity}")
	private int queueCapacity;

	@Bean(name = "mailAsync")
	public Executor mailAsync() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.setThreadNamePrefix("Executor-");
		executor.initialize();
		return executor;
	}
}
