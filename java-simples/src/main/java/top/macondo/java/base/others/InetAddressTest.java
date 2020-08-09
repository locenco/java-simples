package top.macondo.java.base.others;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: zhangchong
 * @Date: 11:10
 **/
public class InetAddressTest {
	@Test
	public void test() throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		System.out.println("Local HostAddress: "+addr.getHostAddress());
				String hostname = addr.getHostName();
		System.out.println("Local host name: "+hostname);
	}
}
