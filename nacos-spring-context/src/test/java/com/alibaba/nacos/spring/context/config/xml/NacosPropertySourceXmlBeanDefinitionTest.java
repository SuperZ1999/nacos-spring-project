package com.alibaba.nacos.spring.context.config.xml;

import org.junit.Assert;
import org.junit.Test;

public class NacosPropertySourceXmlBeanDefinitionTest {
    @Test
    public void test() {
        NacosPropertySourceXmlBeanDefinition beanDefinition = new NacosPropertySourceXmlBeanDefinition();
        Assert.assertNotNull(beanDefinition);
    }
}
