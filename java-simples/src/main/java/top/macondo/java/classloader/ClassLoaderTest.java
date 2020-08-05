package top.macondo.java.classloader;

import org.junit.Test;
import sun.applet.AppletClassLoader;

/**
 * @author: zhangchong
 * @Date: 2020/8/4 20:36
 **/
public class ClassLoaderTest {
	@Test
	public void testBootstrapClassLoader(){
		System.out.println(String.class.getClassLoader());
		System.out.println(System.getProperty("sun.boot.class.path"));
	}
	@Test
	public void testExtClassLoader(){
		System.out.println(System.getProperty("java.ext.dirs"));
	}
	@Test
	public void testSystemClassLoader(){
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(AppletClassLoader.class.getClassLoader());
	}
	@Test
	public void testContextClassLoader(){
		System.out.println(Thread.currentThread().getContextClassLoader());
	}
}