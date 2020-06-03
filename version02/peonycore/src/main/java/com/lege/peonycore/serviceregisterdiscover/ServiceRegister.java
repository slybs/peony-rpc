package com.lege.peonycore.serviceregisterdiscover;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author 了个
 * @date 2020/6/2 18:37
 */
@Slf4j
public class ServiceRegister {
    private ZooKeeper zk;
    private String dataPath;

    public ServiceRegister(String registerAddress, String dataPath) {
        this.dataPath = dataPath;
        try {
            zk = new ZooKeeper(registerAddress, 5000, (event) -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    log.info("zookeeper建立连接");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void register(String data) {
        if (data != null) {
            byte[] bytes = data.getBytes();
            try {
                if (zk.exists(dataPath, null) == null) {
                    zk.create(dataPath, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
                zk.create(dataPath + "/data", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
