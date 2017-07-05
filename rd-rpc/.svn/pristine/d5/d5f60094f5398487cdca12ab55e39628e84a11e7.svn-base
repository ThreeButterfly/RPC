package com.cdrundle.rpc.web;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by tjee on 2017/6/21.
 */
@Component
public class HessianScannerComponet implements InitializingBean, ApplicationContextAware {
	private static Logger logger = LoggerFactory.getLogger(HessianScannerComponet.class);
	private String basePackage = "com.cdrundle.rpc";
	private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
	private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
	ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public String getBasePackage() {
		return this.basePackage;
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	private String resolveBasePackage(String basePackage) {
		return ClassUtils.convertClassNameToResourcePath(
				this.applicationContext.getEnvironment().resolveRequiredPlaceholders(basePackage));
	}

	private Set<BeanDefinition> findCandidateComponents(String basePackage, Class<?> annClass) {
		Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
		try {
			String packageSearchPath = "classpath*:" + resolveBasePackage(basePackage) + "/" + "**/*.class";

			Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
			for (Resource resource : resources) {
				if (resource.isReadable())
					try {
						MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
						ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader);
						sbd.setResource(resource);
						sbd.setSource(resource);

						GenericBeanDefinition definition = sbd;
						Class<?> entityClazz = Class.forName(definition.getBeanClassName());
						HessianService annotation = (HessianService) entityClazz.getAnnotation(HessianService.class);

						if (annotation != null)
							candidates.add(sbd);
					} catch (Throwable ex) {
						throw new BeanDefinitionStoreException("Failed to read candidate component class: " + resource,
								ex);
					}
			}
		} catch (IOException ex) {
			throw new BeanDefinitionStoreException("BeanDefinitionStoreException during classpath scanning", ex);
		}
		return candidates;
	}

	public void afterPropertiesSet() throws Exception {
		// 注册servlet
		ServletContext sc = ((WebApplicationContext) applicationContext).getServletContext();
		HttpHessianServlet httpHessianServlet = sc.createServlet(HttpHessianServlet.class);
		Map<String, HttpRequestHandler> requestHandlerMap = new HashMap<String, HttpRequestHandler>();
		httpHessianServlet.setRequestHandlerMap(requestHandlerMap);
		ServletRegistration sr = sc.addServlet("httpHessianServlet", httpHessianServlet);
		sr.addMapping("/hessian/*");
		// 获取到bean工厂
		DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) this.applicationContext
				.getAutowireCapableBeanFactory();
		// 解释注解
		Set<BeanDefinition> candidateComponents = findCandidateComponents(this.basePackage, HessianService.class);
		for (BeanDefinition beanDefinition : candidateComponents)
			try {
				GenericBeanDefinition definition = (GenericBeanDefinition) beanDefinition;
				Class<?> serviceInterfaceClazz = Class.forName(definition.getBeanClassName());
				Object bean = null;
				HessianService annotation = (HessianService) serviceInterfaceClazz.getAnnotation(HessianService.class);
				String intetface = annotation.Interface();
				String url = annotation.URL();
				if (StringUtils.isNoneBlank(intetface)) {
					serviceInterfaceClazz = Class.forName(intetface);
				}
				try {
					bean = beanFactory.getBean(serviceInterfaceClazz);
				} catch (Exception e) {
					logger.info("处理的hessian service " + serviceInterfaceClazz.getName() + " 在本地没有定义，未找到实例化bean！");
				}
				if (bean != null) {
					String beanName = url;
					if (StringUtils.isBlank(beanName)) {
						beanName = serviceInterfaceClazz.getSimpleName();
						beanName = beanName.substring(0, 1).toLowerCase() + beanName.substring(1);
						url = beanName;
					}
					if (!beanFactory.containsBean(beanName)) {
						BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder
								.rootBeanDefinition(HessianServiceExporter.class);
						AbstractBeanDefinition beanProxyDefinition = beanDefinitionBuilder.getBeanDefinition();
						MutablePropertyValues propertyValues = new MutablePropertyValues();
						propertyValues.addPropertyValue("service", bean);
						propertyValues.addPropertyValue("serviceInterface", serviceInterfaceClazz);
						beanProxyDefinition.setPropertyValues(propertyValues);
						beanFactory.registerBeanDefinition("/" + url, beanProxyDefinition);
					} else {
						logger.debug("beanName:" + beanName + "对应的bean已经存在！");
					}
					logger.info("创建hessian server bean:" + beanName);
					try {
						//注册到工厂
						HessianServiceExporter hessianServiceExporter = beanFactory.getBean("/" + url,
								HessianServiceExporter.class);
						requestHandlerMap.put("/" + beanName, hessianServiceExporter);
					} catch (Exception e) {
						logger.info("创建hessian server bean:" + "/" + url + " 时发生异常！");
						e.printStackTrace();
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}

}
