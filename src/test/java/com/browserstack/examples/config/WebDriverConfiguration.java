package com.browserstack.examples.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

public class WebDriverConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverConfiguration.class);

    private String testEndpoint;

    private DriverType driverType;

    @JsonProperty("localDriver")
    private LocalDriverConfig localDriverConfig;

    @JsonProperty("remoteDriver")
    private RemoteDriverConfig remoteDriverConfig;

    public List<Platform> getActivePlatforms() {
        List<Platform> activePlatforms = Collections.emptyList();
        switch (driverType) {
            case remoteDriver:
                activePlatforms = remoteDriverConfig.getPlatforms();
                break;
            case localDriver:
                activePlatforms = localDriverConfig.getPlatforms();
                break;
        }

        return activePlatforms;
    }

    public String getTestEndpoint() {
        return testEndpoint;
    }

    public void setTestEndpoint(String testEndpoint) {
        this.testEndpoint = testEndpoint;
    }

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public LocalDriverConfig getLocalDriverConfig() {
        return localDriverConfig;
    }

    public void setLocalDriverConfig(LocalDriverConfig localDriverConfig) {
        this.localDriverConfig = localDriverConfig;
    }

    public RemoteDriverConfig getRemoteDriverConfig() {
        return remoteDriverConfig;
    }

    public void setRemoteDriverConfig(RemoteDriverConfig remoteDriverConfig) {
        this.remoteDriverConfig = remoteDriverConfig;
    }
}