package com.zzw.biz.iCache.Refresh;

import com.google.common.base.Stopwatch;
import com.zzw.biz.iCache.Entity.ProductInfo;
import com.zzw.biz.iCache.monitor.monitorConstant;
import com.zzw.iCache.autoconfigure.startup.ThreadLocalUtils;
import com.zzw.iCache.core.Cache.Cache;
import com.zzw.iCache.core.CacheRefresher.CacheRefresh;
import com.zzw.iCache.schedule.core.annotation.SeaDogOverloadProtect;
import com.zzw.iCache.schedule.core.annotation.SeaDogSchedule;
import com.zzw.iCache.schedule.core.constant.ScheduleConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

/**
 * @Author: zhangyang
 * @Data:2024/3/27 17:38
 * @Description:商品缓存刷新的具体实现类
 */
@Slf4j
@Component
@SeaDogSchedule(cacheName = "productCache",beanName = "",cron = "0/10 * * * * ?", startup = true)
//过载保护
//@SeaDogOverloadProtect(strategy = ScheduleConstant.PROJECT_STRATEGY_REDIS)
public class ProductCacheRefreshImpl implements CacheRefresh<ProductInfo> {

    int warnValue=0;

    @Override
    public void refresh(Cache<ProductInfo> cache) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        log.info("开始刷新 商品缓存...");

        //模拟查询商品dubbo接口
        try {
            dubboInvoke();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //写本地缓存
        refreshData(cache);

        log.info("刷新完毕 商品缓存... time:{}", stopwatch.stop());

        //Map<String, BigDecimal> systemMap = threadLocal.get();

        //log.info("系统参数:{}" +systemMap);


        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        // 椎内存使用情况
        MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();

        // 最大可用内存
        long maxMemorySize = memoryUsage.getMax();
        // 已使用的内存
        long usedMemorySize = memoryUsage.getUsed();

        double used = usedMemorySize * 1.0 / 1024.0 / 1024.0;
        double max = maxMemorySize * 1.0 / 1024.0 / 1024.0;

        log.info("已使用的内存(JVM):" + new DecimalFormat("#.#").format(usedMemorySize * 1.0 / 1024.0 / 1024.0) + "M");
        log.info("最大可用内存(JVM):" + new DecimalFormat("#.#").format(maxMemorySize * 1.0 / 1024.0 / 1024.0) + "M");

        BigDecimal bigDecimal = new BigDecimal(used / max * 100).setScale(2, BigDecimal.ROUND_HALF_UP);
        log.info("占内存的大小比率:" +bigDecimal+"%");


        //监控内存告警
        //内存使用率高于80% 同时 告警次数大于10次
        if(bigDecimal.compareTo(new BigDecimal(monitorConstant.MONITOR_WARN_RATE_DEFAULT))>=0) {
            warnValue++;
            if(warnValue>=monitorConstant.MONITOR_WARN_TIMES_DEFAULT) {
                log.error("内存使用率过高,请及时清理缓存");
                //发送到企业微信
            }
        }
    }


    private static void refreshData(Cache<ProductInfo> cache) {


        //生成10w个不相同的商品信息 存入cache中
        for(int i=0;i<10000;i++) {
            String key = DateFormatUtils.format(new Date(), "yyyyMMdd") + "_" + 101 + "_" +"5031"+i;
            ProductInfo productInfo = new ProductInfo();
            productInfo.setAreaId(101);
            productInfo.setProductDesc("这是一个苹果"+i);
            productInfo.setProductName("iPhone"+i);
            productInfo.setSkuSn("5031"+i);

            cache.put(key, productInfo);
        }




    }

    private static void dubboInvoke() throws InterruptedException {

        Thread.sleep(300);
    }
}
