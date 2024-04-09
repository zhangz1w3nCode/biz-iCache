package com.zzw.biz.iCache.Controller;

import com.zzw.biz.iCache.CacheHolder.ProductCacheHolder;
import com.zzw.biz.iCache.CacheHolder.UserCacheHolder;
import com.zzw.biz.iCache.Entity.ProductCacheRequest;
import com.zzw.biz.iCache.Entity.ProductInfo;
import com.zzw.biz.iCache.Entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserCacheHolder userCacheHolder;

    @PostMapping("/put")
    public Object put(@RequestBody User user) {
        return userCacheHolder.addUser(user);
    }

    @PostMapping("/get")
    public Object get(@RequestParam("id") String id) {
        return userCacheHolder.getUser(id);
    }
}
