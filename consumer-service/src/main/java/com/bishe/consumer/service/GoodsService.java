package com.bishe.consumer.service;

import com.bishe.consumer.VO.BillVO;
import com.bishe.consumer.VO.GoodsVO;
import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.entity.GoodsKind;

import java.util.List;

/**
 * @author xujunjie
 */
public interface GoodsService {

    /**
     * 获取账单数据
     */
    List<BillVO> getBill(String userId, String date,Integer page,Integer pageSize);

    /**
     * 获取商品中心数据
     */
    List<GoodsVO> getShopCenter();

    /**
     * 获取商品详细数据
     */
    Goods getOneGoods(Long id);

}
