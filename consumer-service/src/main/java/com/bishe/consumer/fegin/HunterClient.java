package com.bishe.consumer.fegin;

import com.bishe.consumer.entity.Position;
import com.bishe.consumer.utils.BaseRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "HUNTER", fallback = HunterFallBackFactory.class)
public interface HunterClient {

    @PostMapping(value = "/car/updateUserId")
    BaseRes<Object> updateUserId(@RequestParam("carNum") String carNum,
                         @RequestParam("userId") String userId);

    @PostMapping(value = "/car/goCar")
    BaseRes<Object> goCar(Position position);


}
