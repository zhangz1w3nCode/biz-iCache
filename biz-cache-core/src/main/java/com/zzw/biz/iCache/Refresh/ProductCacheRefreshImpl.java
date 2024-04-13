package com.zzw.biz.iCache.Refresh;

import com.google.common.base.Stopwatch;
import com.zzw.biz.iCache.Entity.ProductInfo;
import com.zzw.iCache.core.Cache.Cache;
import com.zzw.iCache.core.CacheRefresher.CacheRefresh;
import com.zzw.iCache.schedule.core.annotation.SeaDogSchedule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: zhangyang
 * @Data:2024/3/27 17:38
 * @Description:缓存刷新器实现类
 */
@Slf4j
@Component
@SeaDogSchedule(cacheName = "productCache",cron = "0/10 * * * * ?", startup = true)
//@SeaDogOverloadProtect(strategy = ScheduleConstant.PROJECT_STRATEGY_REDIS)
public class ProductCacheRefreshImpl implements CacheRefresh<ProductInfo> {

    @Override
    public void refresh(Cache<ProductInfo> cache) {

        Stopwatch stopwatch = Stopwatch.createStarted();

        log.info("开始刷新 商品缓存...");

        refreshData(cache);

        log.info("刷新完毕 商品缓存... time:{}", stopwatch.stop());
    }

    private static void refreshData(Cache<ProductInfo> cache) {


        //生成10w个不相同的商品信息 存入cache中
        for(int i=0;i<100000;i++) {
            String key = DateFormatUtils.format(new Date(), "yyyyMMdd") + "_" + 101 + "_" +"5031"+i;
            ProductInfo productInfo = new ProductInfo();
            productInfo.setAreaId(101);
            productInfo.setProductDesc("这是一个苹果"+i);
            productInfo.setProductName("iPhone"+i);
            productInfo.setSkuSn("5031"+i);

            cache.put(key, productInfo);
        }

    }
}
