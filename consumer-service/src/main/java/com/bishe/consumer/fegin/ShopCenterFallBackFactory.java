package com.bishe.consumer.fegin;

import com.bishe.consumer.VO.BillVO;
import com.bishe.consumer.VO.GoodsVO;
import com.bishe.consumer.entity.Goods;
import com.bishe.consumer.entity.GoodsKind;
import com.bishe.consumer.enums.ResultEnum;
import com.bishe.consumer.exception.AllException;
import com.bishe.consumer.utils.BaseResUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShopCenterFallBackFactory implements ShopCenter{
    @Override
    public List<GoodsVO> getShopCenter() {
        throw new AllException(ResultEnum.RPC_ERROR);
    }
    @Override
    public Goods getOneGoods(Long id) {
        return null;
    }
    @Override
    public List<BillVO> selectAll(String userId, String time, Integer page, Integer pageSize) {
        throw new AllException(ResultEnum.RPC_ERROR);
    }

}
