package com.bishe.consumer.fegin;

import org.springframework.stereotype.Component;

@Component
public class TicketFallBackFactory implements TicketClient {
    @Override
    public String getTicket() {
        return "出现错误";
    }
}
