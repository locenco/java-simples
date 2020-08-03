package top.macondo.java.others.lifecycle;

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
public class ParentBean{
	static {
		System.out.println("ParentBean static");
	}
	
	public ParentBean() {
		System.out.println("ParentBean construct");
	}

}
