package com.alibaba.nacos.spring.context.annotation.config;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;

public class NacosPropertySourceBuilderTest {
    @Test
    public void test() {
        MapPropertySource propertySource = new MapPropertySource("test", new HashMap<>());
        Assert.assertNotNull(propertySource);
    }
}
