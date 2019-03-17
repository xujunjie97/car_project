package com.bishe.consumer.fegin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Code2SessionFallBackFactory implements Code2Session{
    @Override
    public ResponseEntity<String> getOpenId(String appid, String secret, String jsCode, String grantType) {
        log.error("请求微信服务错误");

        return null;
    }
}
