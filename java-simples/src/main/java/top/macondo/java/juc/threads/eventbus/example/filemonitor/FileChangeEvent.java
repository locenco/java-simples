package top.macondo.java.juc.threads.eventbus.example.filemonitor;

import lombok.Getter;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 14:42
 **/
@Getter
public class FileChangeEvent {
	private final Path path;
	private final WatchEvent.Kind<?> kind;

	public FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {
		this.path = path;
		this.kind = kind;
	}

}
