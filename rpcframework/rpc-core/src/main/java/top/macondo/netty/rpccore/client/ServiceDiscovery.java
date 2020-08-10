package top.macondo.netty.rpccore.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import top.macondo.netty.rpccore.pojo.RpcConstant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author: zhangchong
 * @Date: 2020/8/10 17:08
 **/
@Slf4j
public class ServiceDiscovery {
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	private volatile List<String> dataList = new ArrayList<>();
	private final String registryAddress;


	public ServiceDiscovery(String registryAddress) {
		this.registryAddress = registryAddress;
		ZooKeeper zk = connectServer();
		if(zk != null) {
			watchNode(zk);
		}
	}

	public String discover(){
		String data = null;
		int size = dataList.size();
		if (size > 0) {
			if (size == 1){
				data = dataList.get(0);
				log.debug("using only data {}", data);
			}else {
				data = dataList.get(ThreadLocalRandom.current().nextInt(size));
				log.debug("using random data: {}", data);
			}
		}
		return data;
	}
	private ZooKeeper connectServer(){
		ZooKeeper zk = null;
		try {
			zk = new ZooKeeper(registryAddress, RpcConstant.ZK_SESSION_TIMEOUT, new Watcher() {
				@Override
				public void process(WatchedEvent watchedEvent) {
					if (watchedEvent.getState() == Event.KeeperState.SyncConnected){
						countDownLatch.countDown();
					}
				}
			});
			countDownLatch.await();
		} catch (IOException | InterruptedException e) {
			log.error("",e);
		}
		return zk;
	}
	private void watchNode(final ZooKeeper zk){
		try {
			List<String> nodeList = zk.getChildren(RpcConstant.ZK_REGISTRY_PATH,new Watcher(){
				@Override
				public void process(WatchedEvent watchedEvent) {
					if(watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
						watchNode(zk);
					}
				}
			});
			List<String> dataList = new ArrayList<>();
			for (String node : nodeList) {
				byte[] bytes = zk.getData(RpcConstant.ZK_REGISTRY_PATH + "/" + node, false, null);
				dataList.add(new String(bytes));
			}
			log.debug("node data: {}", dataList);
			this.dataList = dataList;
		} catch (InterruptedException|KeeperException e) {
			log.error("", e);
		}
	}

}
