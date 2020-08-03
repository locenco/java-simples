package top.macondo.java.springboot.method_execute_order;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
/**
 * ParentBean static
 *  ParentBean construct
 *  ParentBean init
 *  ParentBean afterPropertiesSet
 *  SonBean static
 *  ParentBean construct
 *  SonBean construct
 *  SonBean init
 *  SonBean afterPropertiesSet
 */
@Component
public class ParentBean implements InitializingBean{
	static {
		System.out.println("ParentBean static");
	}
	
	public ParentBean() {
		System.out.println("ParentBean construct");
	}
	
	@PostConstruct
	public void init(){
		System.out.println("ParentBean init");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("ParentBean afterPropertiesSet");
	}

}
