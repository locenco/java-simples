package top.macondo.java.juc.threads.eventbus.example.filemonitor;

import top.macondo.java.juc.threads.eventbus.subscribe.Subscribe;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 14:47
 **/
public class FileChangeListener {
	@Subscribe
	public void onChange(FileChangeEvent event){
		System.out.printf("%s-%s\n", event.getPath(), event.getKind());
	}
}
