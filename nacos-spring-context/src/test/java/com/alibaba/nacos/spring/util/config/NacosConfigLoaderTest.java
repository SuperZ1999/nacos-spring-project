package com.alibaba.nacos.spring.util.config;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import java.util.Optional;

public class NacosConfigLoaderTest {
    @Test
    public void testNacosConfigLoader() {
        MockEnvironment environment = new MockEnvironment();
        NacosConfigLoader nacosConfigLoader = new NacosConfigLoader(environment);
        Integer convert = environment.getConversionService().convert("12", Integer.class);
        Assert.assertEquals(Integer.valueOf(12), convert);
    }
}
