package com.lege.peonycore.client;

import com.lege.peonycore.common.RpcClientService;
import com.lege.peonycore.serviceregisterdiscover.ServiceDiscovery;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * @Author 了个
 * @date 2020/6/2 18:55
 */
public class ServiceBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        Properties properties = new Properties();
        InputStream is=this.getClass().getResourceAsStream("/application.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String references = properties.getProperty("references");
        String[] prefixs = references.split(":");
        if( prefixs.length == 0)return;
        for (String prefix : prefixs) {
            Set<Class<?>> typesAnnotatedWith = new Reflections(prefix).getTypesAnnotatedWith(RpcClientService.class);
            for (Class beanClazz : typesAnnotatedWith) {
                ////获得注解上的参数信息
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
                GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();

                /**
                 * // 必须在这里加入泛型限定，要不然在spring下会有循环引用的问题
                 */
                definition.getConstructorArgumentValues().addGenericArgumentValue(beanClazz);
                definition.setBeanClass(RpcClinetFactoryBean.class);
                //这里采用的是byType方式注入，类似的还有byName等
                definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
                registry.registerBeanDefinition(beanClazz.getSimpleName(), definition);
            }
        }

    }

//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//        Properties properties = new Properties();
//        InputStream is=this.getClass().getResourceAsStream("/application.properties");
//        try {
//            properties.load(is);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String registerAddress = properties.getProperty("zookeeper.url");
//        String dataPath = properties.getProperty("zookeeper.register.path.prefix");
//        String references = properties.getProperty("references");
//        String[] prefixs = references.split(":");
//        if(prefixs == null || prefixs.length == 0)return;
//        for (String prefix : prefixs) {
//            Set<Class<?>> typesAnnotatedWith = new Reflections(prefix).getTypesAnnotatedWith(RpcClientService.class);
//            for (Class beanClazz : typesAnnotatedWith) {
//                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
//                GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
//
//                //在这里，我们可以给该对象的属性注入对应的实例。
//                //比如mybatis，就在这里注入了dataSource和sqlSessionFactory，
//                // 注意，如果采用definition.getPropertyValues()方式的话，
//                // 类似definition.getPropertyValues().add("interfaceType", beanClazz);
//                // 则要求在FactoryBean（本应用中即RpcProxy）提供setter方法，否则会注入失败
//                // 如果采用definition.getConstructorArgumentValues()，
//                // 则FactoryBean中需要提供包含该属性的构造方法，否则会注入失败
//
//                ServiceDiscovery serviceDiscovery = new ServiceDiscovery(registerAddress, dataPath);
//                //需要在FactoryBean的实现RpcProxy中提供serviceDiscovery属性的setter方法进行诸如操作
//                definition.getPropertyValues().addPropertyValue("serviceDiscovery", serviceDiscovery);
//                /**RpcProxy有如下构造方法：将interfaceType传进去
//                 * 	public RpcProxy(Class<T> interfaceType) {
//                 * 		this.interfaceType = interfaceType;
//                 *  }
//                 */
//                definition.getConstructorArgumentValues().addGenericArgumentValue(beanClazz);
//
//                // 注意，这里的BeanClass是生成Bean实例的工厂，不是Bean本身。
//                // FactoryBean是一种特殊的Bean，其返回的对象不是指定类的一个实例，
//                // 其返回的是该工厂Bean的getObject方法所返回的对象。
//                // RpcProxy实现了FactoryBean
//                definition.setBeanClass(RpcProxy.class);
//
//                //这里采用的是byType方式注入，类似的还有byName等
//                definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
//                registry.registerBeanDefinition(beanClazz.getSimpleName(), definition);
//            }
//        }
//
//    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}