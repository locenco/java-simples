package top.macondo.java.juc.threads.evendriven.simple;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: zhangchong
 * @Date: 2020/8/8 22:21
 **/
@Data
@AllArgsConstructor
public class Event {
	private String type;
	private String data;

}
