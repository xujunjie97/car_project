package com.bishe.hunter.fegin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CarControlFallBack implements CarControl{

    @Override
    public ResponseEntity<String> controlCar(String X, String Y) {
        return null;
    }
}
