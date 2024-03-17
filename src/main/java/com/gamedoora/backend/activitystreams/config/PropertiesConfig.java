
package com.gamedoora.backend.activitystreams.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author aprajshekhar
 */
@Configuration
@RefreshScope
@Order(1)
//@ConfigurationProperties
public class PropertiesConfig {

   private String getStreamAppId;
    private String getStreamAppSecret;

    public String getGetStreamAppId() {
        return getStreamAppId;
    }
    @Value("${activitystreams.app.id}")
    public void setGetStreamAppId(String getStreamAppId) {
        this.getStreamAppId = getStreamAppId;
    }

    public String getGetStreamAppSecret() {
        return getStreamAppSecret;
    }
    @Value("${activitystreams.app.secret}")
    public void setGetStreamAppSecret(String getStreamAppSecret) {
        this.getStreamAppSecret = getStreamAppSecret;
    }
}
