package com.mojito.mojitoboot.common.fortest;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "com.example")
@Data
@Component
public class ConfigBean {
	
	private String name;
	private String desc;

    public ConfigBean() {
    }

    public ConfigBean(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

}
