package com.mud.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by leeesven on 17/8/20.
 */

@Component
@ConfigurationProperties("sgProps")
public class SGProps {

    private String MDSecret;

    public String getMDSecret() {
        return MDSecret;
    }

    public void setMDSecret(String MDSecret) {
        this.MDSecret = MDSecret;
    }
}
