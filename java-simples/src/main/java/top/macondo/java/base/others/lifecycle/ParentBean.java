package top.macondo.java.base.others.lifecycle;

import org.springframework.stereotype.Component;

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
