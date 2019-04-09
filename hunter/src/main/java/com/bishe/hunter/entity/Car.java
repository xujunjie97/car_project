package com.bishe.hunter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xujunjie
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    private Long id;

    private String carNum;

    private String password;

    private int carStatus;

    private String userId;
}
