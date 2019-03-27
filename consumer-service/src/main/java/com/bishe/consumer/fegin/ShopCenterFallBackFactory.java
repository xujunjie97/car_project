package com.bishe.consumer.fegin;

import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.entity.GoodsKind;

import java.util.List;

public class ShopCenterFallBackFactory implements ShopCenter{
    @Override
    public List<GoodsKind> getShopCenter() {
        return null;
    }
    @Override
    public Goods getOneGoods(Long id) {
        return null;
    }
}
