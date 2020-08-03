package top.macondo.java.springboot.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 执行顺序从上往下
 * @author: zhangchong
 * @Date: 15:21
 **/
@Component
public class LifeCycle implements InitializingBean, DisposableBean, ApplicationContextAware {
	static {
		System.out.println("static");
	}
	{
		System.out.println("code block");
	}
	public LifeCycle() {
		System.out.println("Constructor no params");
	}

	/**
	 * #######  aware接口
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("ApplicationContextAware.setApplicationContext");
	}

	/**
	 * BeanPostProcessor.postProcessBeforeInitialization
	 */
	@PostConstruct
	public void init() {
		System.out.println("@PostConstruct");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean.afterPropertiesSet()");
	}

	/**
	 * BeanPostProcessor.postProcessAfterInitialization
	 */

	@PreDestroy
	public void preDestroy() throws Exception {
		System.out.println("@PreDestroy");
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean.destroy()");
	}


}
