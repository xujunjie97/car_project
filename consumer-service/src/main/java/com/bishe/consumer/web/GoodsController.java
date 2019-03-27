package com.bishe.consumer.web;

import com.bishe.consumer.service.GoodsService;
import com.bishe.consumer.utils.BaseRes;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return null;
    }

}
