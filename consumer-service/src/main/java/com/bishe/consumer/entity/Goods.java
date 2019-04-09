package com.bishe.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xujunjie
 * 商品类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Goods {

    private Long id;

    private Long kindId;

    private String thumb;

    private String name;

    private String images;

    private String title;

    private BigDecimal price;
}
