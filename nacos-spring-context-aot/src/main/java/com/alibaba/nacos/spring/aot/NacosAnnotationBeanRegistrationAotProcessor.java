package com.alibaba.nacos.spring.aot;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.aot.generate.GeneratedClass;
import org.springframework.aot.generate.GeneratedMethod;
import org.springframework.aot.generate.GenerationContext;
import org.springframework.beans.factory.aot.BeanRegistrationAotContribution;
import org.springframework.beans.factory.aot.BeanRegistrationAotProcessor;
import org.springframework.beans.factory.aot.BeanRegistrationCode;
import org.springframework.beans.factory.support.RegisteredBean;
import org.springframework.javapoet.CodeBlock;
import org.springframework.util.ReflectionUtils;

import javax.lang.model.element.Modifier;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NacosAnnotationBeanRegistrationAotProcessor implements BeanRegistrationAotProcessor {
    @Override
    public BeanRegistrationAotContribution processAheadOfTime(RegisteredBean registeredBean) {
        Class<?> beanClass = registeredBean.getBeanClass();
        ArrayList<Field> fields = new ArrayList<>();
        ReflectionUtils.doWithFields(beanClass, field -> {
            NacosInjected injectedAnnotation = field.getDeclaredAnnotation(NacosInjected.class);
            NacosValue nacosValueAnnotation = field.getDeclaredAnnotation(NacosValue.class);
            if (injectedAnnotation != null || nacosValueAnnotation != null) {
                fields.add(field);
            }
        });
        return new AotContribution(fields);
    }

    private static class AotContribution implements BeanRegistrationAotContribution {
        private List<Field> fields;

        public AotContribution() {
            this.fields = new ArrayList<>();
        }

        public AotContribution(List<Field> fields) {
            this.fields = fields;
        }

        @Override
        public void applyTo(GenerationContext generationContext, BeanRegistrationCode beanRegistrationCode) {
            System.out.println("进入applyTo:" + beanRegistrationCode.getMethods());
            for (Field field : fields) {
                generationContext.getRuntimeHints().reflection().registerField(field);
            }
        }
    }
}
