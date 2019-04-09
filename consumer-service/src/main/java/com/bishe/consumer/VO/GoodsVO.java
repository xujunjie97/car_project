package com.bishe.consumer.VO;


import com.bishe.consumer.entity.Goods;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.Banner;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class GoodsVO {

    private long id;
    private String banner;
    private String title;
    private List<Goods> productlist;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Goods> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<Goods> productlist) {
        this.productlist = productlist;
    }
}
