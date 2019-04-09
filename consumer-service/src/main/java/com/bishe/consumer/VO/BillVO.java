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

    private Date time;

    private String totalPrice;

    private List<OrderDetail> billDetail;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
