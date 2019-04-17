package com.bishe.consumer.VO;


import com.bishe.consumer.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillVO {

    private String time;

    private String totalPrice;

    private List<OrderDetail> billDetail;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetail> getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(List<OrderDetail> listdetail) {
        this.billDetail = listdetail;
    }
}
