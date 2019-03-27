package com.bishe.consumer.service;

import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.entity.GoodsKind;

import java.util.List;

/**
 * @author xujunjie
 */
public interface GoodsService {

    /**
     * 获取商品中心数据
     */
    List<GoodsKind> getShopCenter();


    /**
     * 获取商品详细数据
     */
    Goods getOneGoods(Long id);

}
