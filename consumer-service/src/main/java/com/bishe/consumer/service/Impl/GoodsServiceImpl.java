package com.bishe.consumer.service.Impl;

import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.entity.GoodsKind;
import com.bishe.consumer.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xujunjie
 */
@Service
public class GoodsServiceImpl implements GoodsService{
    /**
     * 获取商品中心数据
     */
    @Override
    public List<GoodsKind> getShopCenter() {
        //todo 直接调用超市后台数据

        return null;
    }
    /**
     * 获取商品详细数据
     *
     * @param id
     */
    @Override
    public Goods getOneGoods(Long id) {


        return null;
    }
}
