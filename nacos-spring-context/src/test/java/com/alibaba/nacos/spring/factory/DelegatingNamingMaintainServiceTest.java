package com.alibaba.nacos.spring.factory;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.pojo.Service;
import com.alibaba.nacos.client.naming.NacosNamingMaintainService;
import org.junit.Assert;
import org.junit.Test;

public class DelegatingNamingMaintainServiceTest {
    @Test
    public void testNamingMaintainService() throws NacosException {
        NacosNamingMaintainService namingMaintainService = new NacosNamingMaintainService("127.0.0.1:8848");
        Service service = namingMaintainService.queryService("example");
        Assert.assertNotNull(service);
    }
}
