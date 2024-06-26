package com.zzw.biz.iCache.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo implements Serializable {
    int areaId;
    String skuSn;
    String productName;
    String productDesc;
}
