package com.zzw.biz.iCache.CacheHolder;


import com.zzw.biz.iCache.Entity.ProductInfo;
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
public class ProductCacheHolder {


    @iCache("productCache")
    private Cache<ProductInfo> productCache;


    /**
     * 获取当前区域、销售日期skuSn对应的商品
     *
     * @return
     */
    public ProductInfo getProductBySkuSn(int areaId, Date  tmAccount, String skuSn) {
        return productCache.get(buildAreaSkuKey(areaId, tmAccount, skuSn));
    }

    /**
     * 批量增加或更新商品
     */
    public void addProductList(List<ProductInfo> productInfoList, Date saleDate) {
        productInfoList.forEach(product -> addProduct(product, saleDate));
    }

    /**
     * 增加或更新商品
     */
    public Object addProduct(ProductInfo productInfo, Date saleDate) {

        // 1、构建本地缓存key
        String cacheKey = buildAreaSkuKey(productInfo.getAreaId(), saleDate, productInfo.getSkuSn());

        // 2、放入缓存
        productCache.put(cacheKey, productInfo);

        return cacheKey;
    }


    /**
     * 商品信息本地缓存KEY
     */
    private String buildAreaSkuKey(int areaId, Date tmAccount, String skuSn) {
        return DateFormatUtils.format(tmAccount, "yyyyMMdd") + "_" + areaId + "_" + skuSn;
    }

}