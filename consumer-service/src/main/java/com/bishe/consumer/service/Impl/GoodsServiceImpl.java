package com.bishe.consumer.service.Impl;

import com.bishe.consumer.VO.BillVO;
import com.bishe.consumer.VO.GoodsVO;
import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.fegin.ShopCenter;
import com.bishe.consumer.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xujunjie
 */
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private ShopCenter shopCenter;

    /**
     * 获取商品中心数据
     */
    @Override
    public List<BillVO> getBill(String userId,String date,Integer page,Integer pageSize) {

        return shopCenter.selectAll(userId, date,page,pageSize);
    }
    /**
     * 获取商品中心数据
     */
    @Override
    public List<GoodsVO> getShopCenter() {

        return shopCenter.getShopCenter();

    }
    /**
     * 获取商品详细数据
     */
    @Override
    public Goods getOneGoods(Long id) {
        return shopCenter.getOneGoods(id);
    }
}
