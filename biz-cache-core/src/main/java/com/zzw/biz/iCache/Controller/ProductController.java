package com.zzw.biz.iCache.Controller;

import com.zzw.biz.iCache.CacheHolder.ProductCacheHolder;
import com.zzw.biz.iCache.Entity.ProductCacheRequest;
import com.zzw.biz.iCache.Entity.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@RestController
@RequestMapping("/biz")
public class ProductController {

    @Autowired
    private ProductCacheHolder cacheHolder;

    @PostMapping("/put")
    public Object put(@RequestBody ProductInfo productInfo) {
        return cacheHolder.addProduct(productInfo,new Date());
    }

    @PostMapping("/get")
    public Object get(@RequestBody ProductCacheRequest request) {
        return cacheHolder.getProductBySkuSn(request.getAreaId(),request.getTmAccount(),request.getSkuSn());
    }
}
