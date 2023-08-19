package com.alibaba.nacos.spring.context.properties.config;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;

public class DataBinderTest {
    @Test
    public void test() throws BindException {
        People people = new People();
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("name", "SuperZ1999");
        propertyValues.add("age", 24);
        DataBinder dataBinder = new DataBinder(people);
        dataBinder.setAutoGrowNestedPaths(false);
        dataBinder.setIgnoreInvalidFields(false);
        dataBinder.setIgnoreUnknownFields(true);
        dataBinder.bind(propertyValues);
        dataBinder.close();

        Assert.assertEquals("SuperZ1999", people.getName());
        Assert.assertEquals(24, people.getAge());
    }

    static class People {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "People{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
