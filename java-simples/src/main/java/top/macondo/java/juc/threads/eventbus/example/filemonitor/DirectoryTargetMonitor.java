package top.macondo.java.juc.threads.eventbus.example.filemonitor;

import top.macondo.java.juc.threads.eventbus.bus.EventBus;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author: zhangchong
 * @Date: 2020/8/9 14:31
 **/
public class DirectoryTargetMonitor {
	private WatchService watchService;

	private final EventBus eventBus;
	private final Path path;

	private volatile boolean start = false;

	public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath){
		this(eventBus, targetPath, "");
	}

	public DirectoryTargetMonitor(final EventBus eventBus, final String targetPath, String... morePaths) {
		this.eventBus = eventBus;
		this.path = Paths.get(targetPath, morePaths);
	}

	public void startMonitor() throws IOException {
		this.watchService = FileSystems.getDefault().newWatchService();
		this.path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY,
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_CREATE);
		System.out.printf("The directory [%s] is monitoring... \n", path);
		this.start = true;
		while (start){
			WatchKey watchKey = null;
			try {
				watchKey = watchService.take();
				watchKey.pollEvents().forEach(event -> {
					WatchEvent.Kind<?> kind = event.kind();
					Path path = (Path) event.context();
					Path child = DirectoryTargetMonitor.this.path.resolve(path);
					eventBus.post(new FileChangeEvent(child,kind));
				});
			} catch (InterruptedException e) {
				this.start = false;
			}finally {
				if (watchKey != null){
					watchKey.reset();
				}
			}

		}
	}
	public void stopMonitor() throws IOException {
		System.out.printf("The directory [%s] monitor will be stop...\n",path);
		Thread.currentThread().interrupt();
		this.start = false;
		this.watchService.close();
		System.out.printf("'The directory [%s] monitor will be stop done.\n", path);
	}
}
