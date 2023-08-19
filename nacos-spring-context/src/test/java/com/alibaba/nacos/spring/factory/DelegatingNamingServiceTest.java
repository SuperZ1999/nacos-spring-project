package com.alibaba.nacos.spring.factory;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class DelegatingNamingServiceTest {
    @Test
    public void testNamingService() throws NacosException {
        NacosNamingService nacosNamingService = new NacosNamingService("127.0.0.1:8848");
        List<Instance> instances = nacosNamingService.getAllInstances("example");
        System.out.println(instances);
        Assert.assertNotNull(instances);
    }
}
