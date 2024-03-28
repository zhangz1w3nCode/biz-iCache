package com.zzw.iCache.CacheConstant;

/**
 * 框架常量
 */
public interface CacheConstant {

    String REAL_CACHE = "realcache";

    String CACHE_TYPE_CAFFEINE = "CAFFEINE";

    String CACHE_TYPE_CONCURRENT_HASH_MAP = "CHMAP";

    String REFRESH_METHOD_NAME = "refresh";

    /**
     *  一天的秒数
     */
    int SECONDS_OF_DAY = 60 * 60 * 24;
}