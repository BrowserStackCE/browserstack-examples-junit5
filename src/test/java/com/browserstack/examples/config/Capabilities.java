package com.browserstack.examples.config;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;


public class Capabilities {

    private Map<String, Object> capabilityMap = new LinkedHashMap<>();

    @JsonAnySetter
    public void setCapabilities(String key, Object value) {
        this.capabilityMap.put(key, value);
    }

    public Object capability(String key) {
        return this.capabilityMap.get(key);
    }

    public Map<String, Object> getCapabilityMap() {
        return capabilityMap;
    }
}
