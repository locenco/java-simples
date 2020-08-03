package top.macondo.java.springboot.method_execute_order;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SonBean extends ParentBean{
	static {
		System.out.println("SonBean static");
	}
	public SonBean() {
		System.out.println("SonBean construct");
	}
	
	@Override
	@PostConstruct
	public void init(){
		System.out.println("SonBean init");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("SonBean afterPropertiesSet");
	}
	
}