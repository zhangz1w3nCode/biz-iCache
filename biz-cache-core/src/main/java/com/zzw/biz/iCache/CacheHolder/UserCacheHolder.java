package com.zzw.biz.iCache.CacheHolder;


import com.zzw.biz.iCache.Entity.ProductInfo;
import com.zzw.biz.iCache.Entity.User;
import com.zzw.iCache.autoconfigure.annocation.iCache;
import com.zzw.iCache.core.Cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 商品缓存 持有者
 *
 * @author qiaolin
 * @version v 0.1 2021年07月13日 16:48
 */
@Slf4j
@Component
public class UserCacheHolder {


    @iCache("userCache")
    private Cache<User> userCache;


    public User getUser(String userId) {
        return userCache.get(userId);
    }



    /**
     * 增加或更新商品
     */
    public Object addUser(User user) {

        // 2、放入缓存
        userCache.put(user.getId(), user);

        return user.getId();
    }

}