package com.bishe.consumer.web;

import com.bishe.consumer.VO.GoodsVO;
import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.service.GoodsService;
import com.bishe.consumer.utils.BaseRes;
import com.bishe.consumer.utils.BaseResUtil;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xujunjie
 */
@RestController
@Slf4j
@RequestMapping("/Goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/getShopCenter")
    public BaseRes shopCenter() {
        List<GoodsVO> list = goodsService.getShopCenter();
        log.info("商品数据={}",list);
        return BaseResUtil.success(list);
    }

    /**
     * 获取单个商品数据
     * @param goodsId 商品ID
     */
    @PostMapping("/getOneGoods")
    public BaseRes getOneGoods(Long goodsId){
        Goods goods = goodsService.getOneGoods(goodsId);
        log.info("goods={}",goods);
        return BaseResUtil.success(goods);

    }

}
