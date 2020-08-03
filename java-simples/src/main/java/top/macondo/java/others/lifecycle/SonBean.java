package top.macondo.java.others.lifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SonBean  {
	static {
		System.out.println("SonBean static");
	}
	public SonBean() {
		System.out.println("SonBean construct");
	}
	
}