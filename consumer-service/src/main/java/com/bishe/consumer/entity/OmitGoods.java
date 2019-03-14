package com.bishe.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author xujunjie
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OmitGoods {

    private Long kindId;

    private String thumb;

    private String name;
}
