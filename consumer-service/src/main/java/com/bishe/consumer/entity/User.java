package com.bishe.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xujunjie
 * 用户类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    private String nickName;

    private String gender;

    private String country;

    private String city;

    private String openId;

    private String carNum;


}
