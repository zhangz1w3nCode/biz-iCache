//package com.zzw.biz.iCache.Refresh;
//
//import com.google.common.base.Stopwatch;
//import com.zzw.biz.iCache.Entity.ProductInfo;
//import com.zzw.iCache.core.Cache.Cache;
//import com.zzw.iCache.core.CacheRefresher.CacheRefresh;
//import com.zzw.iCache.schedule.core.annotation.SeaDogSchedule;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * @Author: zhangyang
// * @Data:2024/3/27 17:38
// * @Description: 模拟 多线程 定时任务 缓存快照入库
// */
//@Slf4j
//@Component
////@SeaDogSchedule(cacheName = "productCache",cron = "0/10 * * * * ?", startup = true)
////过载保护
////@SeaDogOverloadProtect(strategy = ScheduleConstant.PROJECT_STRATEGY_REDIS)
//public class SnapshotRefreshImpl implements CacheRefresh<ProductInfo> {
//
//    @Override
//    public void refresh(Cache<ProductInfo> cache) {
//
//    }
//
//
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void scheduled() throws InterruptedException {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        log.info("开始插入商品快照...");
//        buildData();
//        log.info("插入商品快照结束... time:{}", stopwatch.stop());
//    }
//
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void scheduled1() throws InterruptedException {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        log.info("开始插入规则快照...");
//        buildData();
//        log.info("插入商品快照结束... time:{}", stopwatch.stop());
//    }
//
//    @Scheduled(cron = "0/10 * * * * ?")
//    public void scheduled2() throws InterruptedException {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        log.info("开始插入其他快照...");
//        buildData();
//        log.info("插入商品快照结束... time:{}", stopwatch.stop());
//    }
//
//
//    public void buildData() throws InterruptedException {
//        List<ProductInfo> productInfos = new ArrayList<>();
//        for(int i=0;i<300000;i++) {
//            String key = DateFormatUtils.format(new Date(), "yyyyMMdd") + "_" + 101 + "_" +"5031"+i;
//            ProductInfo productInfo = new ProductInfo();
//            productInfo.setAreaId(101);
//            productInfo.setProductDesc("这是一个苹果"+i);
//            productInfo.setProductName("iPhone"+i);
//            productInfo.setSkuSn("5031"+i);
//            productInfos.add(productInfo);
//        }
//        Thread.sleep(1000*30);
//    }
//}
