package com.bishe.hunter.fegin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "caruyh", url = "127.0.0.1:5000",fallback = CarControlFallBack.class)
public interface CarControl {

    @RequestMapping(value = "/location",method = RequestMethod.GET)
    ResponseEntity<String> controlCar(@RequestParam("X") String X,
                                      @RequestParam("Y") String Y);
}
