package com.bishe.comsumer.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "PROVIDER-TICKET",fallback = TicketFallBackFactory.class)
public interface TicketClient {

    @RequestMapping(method = RequestMethod.GET,value = "/ticket")
    String getTicket();
}
