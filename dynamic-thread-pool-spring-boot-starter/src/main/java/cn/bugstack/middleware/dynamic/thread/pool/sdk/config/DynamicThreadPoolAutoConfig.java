package cn.bugstack.middleware.dynamic.thread.pool.sdk.config;

import cn.bugstack.middleware.dynamic.thread.pool.sdk.domain.DynamicThreadPoolService;
import com.alibaba.fastjson.JSON;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

//动态配置入口
@Configuration
public class DynamicThreadPoolAutoConfig {

    private final Logger logger = LoggerFactory.getLogger(DynamicThreadPoolAutoConfig.class);//警告日志

    @Bean("dynamicThreadPollService")
    public DynamicThreadPoolService dynamicThreadPollService(ApplicationContext applicationContext, Map<String,ThreadPoolExecutor > threadPoolExecutorMap) {
        String applicationName = applicationContext.getEnvironment().getProperty("spring.application.name");
        if (StringUtils.isBlank(applicationName)) {applicationName = "缺省的";
        logger.warn("动态线程池，启动提示。SpringBoot 应用未配置 spring.application.name 无法获取到应用名称！");
    }

        // 获取缓存数据，设置本地线程池配置
//        Set<String> threadPoolKeys = threadPoolExecutorMap.keySet();
//        for(String threadPoolKey :threadPoolKeys){
//            ThreadPoolExecutor threadPoolExecutor = threadPoolExecutorMap.get(threadPoolKey);
//            int poolSize = threadPoolExecutor.getPoolSize();
//            int corePoolSize = threadPoolExecutor.getCorePoolSize();
//            BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
//            String simpleName = queue.getClass().getSimpleName();//SimpleName queue type
//        }
//
//        logger.info("线程池信息:{}", JSON.toJSONString(threadPoolExecutorMap.keySet()));

        return new DynamicThreadPoolService(applicationName,threadPoolExecutorMap);

    }

}
