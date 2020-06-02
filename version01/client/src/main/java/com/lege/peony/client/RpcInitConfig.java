package com.lege.peony.client;

import com.lege.peony.common.annotation.RpcClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Set;

/**
 * @Author 了个
 * @date 2020/5/26 19:15
 */

public class RpcInitConfig implements ImportBeanDefinitionRegistrar {
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider provider = getScanner();
        //设置扫描器
        provider.addIncludeFilter(new AnnotationTypeFilter(RpcClient.class));
        //TODO 扫描此包下的所有带有@RpcClient的注解的类
        System.out.println(System.getProperty("scan"));
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("com.lege.demoserverapi");
        for (BeanDefinition beanDefinition : beanDefinitionSet) {
            if (beanDefinition instanceof AnnotatedBeanDefinition) {
                //获得注解上的参数信息
                String beanClassAllName = beanDefinition.getBeanClassName();
                //将RpcClient的工厂类注册进去
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(RpcClinetFactoryBean.class);
                //设置RpcClinetFactoryBean工厂类中的构造函数的值
                builder.addConstructorArgValue(beanClassAllName);
                builder.getBeanDefinition().setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                //将其注册进容器中
                registry.registerBeanDefinition(beanClassAllName, builder.getBeanDefinition());
            }
        }
    }

    //允许Spring扫描接口上的注解
    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
            }
        };
    }
}
