package com.alibaba.nacos.spring.context.annotation.discovery;

import com.alibaba.nacos.spring.context.annotation.EnableNacos;
import com.alibaba.nacos.spring.util.aot.AotDetector;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

import static com.alibaba.nacos.spring.util.NacosBeanUtils.*;

public class EnableNacosDiscoveryAotProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {
    private Environment environment;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        if (AotDetector.useGeneratedArtifacts()) {
            System.out.println("postProcessBeanDefinitionRegistry");
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) registry;
            Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(EnableNacosDiscovery.class);
            for (Object bean : beansWithAnnotation.values()) {
                // TODO jdk动态代理类怎么办？
                Class<?> aClass = bean.getClass();
                System.out.println(aClass);
                if (aClass.getAnnotation(EnableNacosDiscovery.class) == null) {
                    aClass = aClass.getSuperclass();
                    System.out.println(aClass);
                }
                AnnotationMetadata annotationMetadata = AnnotationMetadata.introspect(aClass);
                AnnotationAttributes attributes = AnnotationAttributes
                        .fromMap(annotationMetadata
                                .getAnnotationAttributes(EnableNacosDiscovery.class.getName()));

                System.out.println(attributes);
                // Register Global Nacos Properties Bean
                registerGlobalNacosProperties(attributes, beanFactory, environment,
                        DISCOVERY_GLOBAL_NACOS_PROPERTIES_BEAN_NAME);
                registerGlobalNacosProperties(attributes, beanFactory, environment,
                        MAINTAIN_GLOBAL_NACOS_PROPERTIES_BEAN_NAME);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
