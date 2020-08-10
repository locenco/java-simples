package top.macondo.netty.rpccore.server;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import top.macondo.netty.rpccore.pojo.RpcConstant;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 服务注册，
 * @author: zhangchong
 * @Date: 2020/8/10 15:09
 **/
@Slf4j
public class ServiceRegistry {
	private CountDownLatch countDownLatch = new CountDownLatch(1);
	private final String registryAddress;

	public ServiceRegistry(String registryAddress) {
		this.registryAddress = registryAddress;
	}
	public void register(String data){
		if(data != null){
			ZooKeeper zk = connectServer();
			if(zk != null){
				createNode(zk,data);
			}
		}
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

	/**
	 *
	 * @param zk
	 * @param data
	 */
	private void createNode(ZooKeeper zk, String data){
		try {
			byte[] bytes = data.getBytes();
			String path = zk.create(RpcConstant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
			log.debug("create zookeeper node ({} => {})", path, data);
		} catch (KeeperException |InterruptedException e) {
			log.error("",e);
		}

	}
}
