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
public class Admin {

    private Long id;

    private String adminName;

    private String passWord;

    private String adminNum;

    private String roles;

}
