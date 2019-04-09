package com.bishe.hunter.fegin;

import com.bishe.hunter.enums.ResultEnum;
import com.bishe.hunter.utils.BaseRes;
import com.bishe.hunter.utils.BaseResUtil;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallBack implements UserClient {
    @Override
    public BaseRes changeUserBind(String openId) {
        return BaseResUtil.error(ResultEnum.BIND_ERROR);
    }
}
