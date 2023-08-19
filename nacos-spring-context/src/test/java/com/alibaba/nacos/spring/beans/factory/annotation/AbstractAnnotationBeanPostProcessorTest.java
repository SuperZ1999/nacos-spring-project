package com.alibaba.nacos.spring.beans.factory.annotation;

import org.junit.Assert;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;

public class AbstractAnnotationBeanPostProcessorTest {
    @Test
    public void testPropertyDescriptor() throws IntrospectionException {
        PropertyDescriptor pd = new PropertyDescriptor("name", Student.class);
        Class<?> type = pd.getPropertyType();
        Assert.assertEquals(type, String.class);
    }

    static class Student {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
