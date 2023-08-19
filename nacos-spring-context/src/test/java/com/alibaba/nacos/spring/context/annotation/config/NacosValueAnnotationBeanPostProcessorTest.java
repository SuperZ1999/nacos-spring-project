package com.alibaba.nacos.spring.context.annotation.config;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Method;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NacosValueAnnotationBeanPostProcessorTest.class)
public class NacosValueAnnotationBeanPostProcessorTest {
    @Autowired
    private NacosValueAnnotationBeanPostProcessor postProcessor;

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    @Bean
    public NacosValueAnnotationBeanPostProcessor nacosValueAnnotationBeanPostProcessor() {
        return new NacosValueAnnotationBeanPostProcessor();
    }

    @Test
    public void testConvertIfNecessary() throws NoSuchMethodException {
        TypeConverter converter = beanFactory.getTypeConverter();
        Method method = NacosValueAnnotationBeanPostProcessorTest.class.getMethod("testMethodParameter", Integer.class);
        Integer integer = converter.convertIfNecessary("12", Integer.class, new MethodParameter(method, 0));
        System.out.println(integer);
        Assert.assertEquals(integer, Integer.valueOf(12));
    }

    public void testMethodParameter(Integer i) {
        System.out.println(i);
    }
}
