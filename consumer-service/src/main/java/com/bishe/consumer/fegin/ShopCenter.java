package com.bishe.consumer.fegin;


import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.entity.GoodsKind;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "market", fallback = ShopCenterFallBackFactory.class)
public interface ShopCenter {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    List<GoodsKind> getShopCenter();

    @RequestMapping(value = "/",method = RequestMethod.GET)
    Goods getOneGoods(@RequestParam("id") Long id);
}
