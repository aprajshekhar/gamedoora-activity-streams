package com.gamedoora.backend.activitystreams.config;

import io.getstream.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class GetStreamConfig {
    private PropertiesConfig propertiesConfig;

    @Bean("getStreamClient")
    Client client() throws MalformedURLException {
        return Client.builder(
                getPropertiesConfig().getGetStreamAppId(),
                getPropertiesConfig().getGetStreamAppSecret()
        ).build();
    }
    public PropertiesConfig getPropertiesConfig() {
        return propertiesConfig;
    }
    @Autowired
    public void setPropertiesConfig(PropertiesConfig propertiesConfig) {
        this.propertiesConfig = propertiesConfig;
    }
}
