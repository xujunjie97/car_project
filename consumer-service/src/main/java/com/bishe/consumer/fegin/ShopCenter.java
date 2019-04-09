package com.bishe.consumer.fegin;


import com.bishe.consumer.VO.BillVO;
import com.bishe.consumer.VO.GoodsVO;
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

    @RequestMapping(value = "/goodsType/select",method = RequestMethod.GET)
    List<GoodsVO> getShopCenter();

    @RequestMapping(value = "/goods/selectOne",method = RequestMethod.POST)
    Goods getOneGoods(@RequestParam("id") Long id);

    @RequestMapping(value = "/bill/getBill",method = RequestMethod.POST)
    List<BillVO> selectAll(@RequestParam("userId") String userId, @RequestParam("time") String time
    ,@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize);


}
