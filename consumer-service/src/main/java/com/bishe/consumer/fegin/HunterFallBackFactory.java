package com.bishe.consumer.fegin;

import com.bishe.consumer.enums.ResultEnum;
import com.bishe.consumer.utils.BaseRes;
import com.bishe.consumer.utils.BaseResUtil;
import org.springframework.stereotype.Component;

@Component
public class HunterFallBackFactory implements HunterClient{
    @Override
    public BaseRes updateUserId(String carNum, String userId) {
        return BaseResUtil.error(ResultEnum.BIND_CAR_ERROR);
    }
}
