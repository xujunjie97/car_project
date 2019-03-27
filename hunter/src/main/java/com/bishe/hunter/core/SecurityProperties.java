package com.bishe.hunter.core;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bishe.security")
@Data
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();
}
