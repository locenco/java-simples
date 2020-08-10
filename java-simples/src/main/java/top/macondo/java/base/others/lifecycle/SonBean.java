package top.macondo.java.base.others.lifecycle;

import org.springframework.stereotype.Component;

@Component
public class SonBean  {
	static {
		System.out.println("SonBean static");
	}
	public SonBean() {
		System.out.println("SonBean construct");
	}
	
}