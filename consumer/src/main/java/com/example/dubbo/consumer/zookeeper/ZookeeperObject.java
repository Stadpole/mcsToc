package com.example.dubbo.consumer.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ZookeeperObject {

    // 属性—————————————————————————————————————————————————————————————————————————————————————————————————————————————
    private String zookeeperGroup = "";  // zookeeper集群的地址，String格式
    private ZooKeeper zk;  // 直接和zookeeper API交互的主体
//
//    /** zk父路径设置 */
//    private static final String PARENT_PATH = "/testWatch";
//    /** zk子路径设置 */
//    private static final String CHILDREN_PATH = "/testWatch/children";

    AtomicInteger seq = new AtomicInteger();
    private CountDownLatch connectedSemaphore = new CountDownLatch(1);

    // 方法————————————————————————————————————————————————————————————————————————————————————-————————————————————————
    /**
     * 从config文件获取zookeeper集群地址，存储到属性
     *
     */
    public void GetZkAddress (){
        zookeeperGroup = "172.16.18.209:2191,172.16.18.208:2192,172.16.18.208:2193";
    }

    /**
     * 连接到zookeeper
     * @param timeout：连接超时时间
     * @return 连接成功会结束，不成功会陷入循环一直尝试连接
     */
    public void ConnectZookeeper(int timeout) throws IOException, KeeperException, InterruptedException {
        zk = new ZooKeeper(zookeeperGroup, timeout, new Watcher() {  // Zookeeper连接
            @Override
            public void process(WatchedEvent watchedEvent) {
                dealAlarm(watchedEvent);
            }
        });

        // 循环判断是否连接成功
        boolean LossFlag = false;
        do {
            LossFlag = false;
            try {
                zk.exists("/test", false);  // 连接后只有使用才会返回是不是连接上了，随便检测一个目录存不存在，结果不重要
            } catch (KeeperException.ConnectionLossException e){
                System.err.println("连接超时，尝试连接");
                LossFlag = true;
            }
        }while(LossFlag);

        System.out.println("ZooKeeper已连接----------------------------------------------------------------------------");
    }

    /**
     * 关闭ZK连接
     */
    public void releaseConnection() {
        if (zk != null) {
            try {
                zk.close();
            } catch (InterruptedException e) {
                System.err.println("关闭连接发生中断");
            }
        }
    }

    /**
     * 创建临时节点
     * @param path 节点路径
     * @param data 数据内容
     * @return
     */
    public boolean createNode(String path, String data) throws KeeperException, InterruptedException {
        Stat s = zk.exists(path, false);
        try {
            //设置监控(由于zookeeper的监控都是一次性的所以 每次必须设置监控)
            if(s != null) {
                return true;
            }
            System.out.println("节点创建成功, 路径为: " + zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL) + ", 数据为:" + data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 读取指定节点数据内容
     * @param path 节点路径
     * @return
     */
    public String readData(String path, boolean needWatch) {
        try {
            return new String(this.zk.getData(path, needWatch, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 更新指定节点数据内容
     * @param path 节点路径
     * @param data 数据内容
     * @return
     */
    public boolean writeData(String path, String data) {
        try {
            System.out.println("更新数据成功，path：" + path + ", stat: " +
                    this.zk.setData(path, data.getBytes(), -1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除指定节点
     *
     * @param path
     *            节点path
     */
    public void deleteNode(String path) {
        try {
            this.zk.delete(path, -1);
            System.out.println("删除节点成功，path：" + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断指定节点是否存在
     * @param path 节点路径
     */
    public Stat exists(String path, boolean needWatch) {
        Stat s = new Stat();
        try {
            s = this.zk.exists(path, needWatch);
            return this.zk.exists(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取子节点
     * @param path 节点路径
     */
    public List<String> getChildren(String path, boolean needWatch) {
        List<String> childrenNode = new ArrayList<>();
        List<String> childrenPath = new ArrayList<>();
        try {
            childrenNode =  this.zk.getChildren(path, needWatch);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        for (int i = 0; i <= childrenNode.size()-1; i++){
            childrenPath.add(i, path + "/" + childrenNode.get(i));  // 将list化为完整路径传回
        }
        return childrenPath;
    }

    /**
     * 删除所有节点
     */
//    public void deleteAllTestPath() {
//        if(this.exists(CHILDREN_PATH, false) != null){
//            this.deleteNode(CHILDREN_PATH);
//        }
//        if(this.exists(PARENT_PATH, false) != null){
//            this.deleteNode(PARENT_PATH);
//        }
//    }

    /**
     * 监视到告警后处理
     * @param watchedEvent
     */
    public void dealAlarm(WatchedEvent watchedEvent){
        System.out.println(watchedEvent.getPath() + "|" + watchedEvent.getType());  // 输出事件
    }

    /**
     * 收到来自Server的Watcher通知后的处理。
     */
    //@Override
    public void process(WatchedEvent event) {

        System.out.println("进入 process 。。。。。event = " + event);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (event == null) {
            return;
        }

        // 连接状态
        Watcher.Event.KeeperState keeperState = event.getState();
        // 事件类型
        Watcher.Event.EventType eventType = event.getType();
        // 受影响的path
        String path = event.getPath();

        String logPrefix = "【Watcher-" + this.seq.incrementAndGet() + "】";

        System.out.println(logPrefix + "收到Watcher通知");
        System.out.println(logPrefix + "连接状态:\t" + keeperState.toString());
        System.out.println(logPrefix + "事件类型:\t" + eventType.toString());

        if (Watcher.Event.KeeperState.SyncConnected == keeperState) {
            // 成功连接上ZK服务器
            if (Watcher.Event.EventType.None == eventType) {
                System.out.println(logPrefix + "成功连接上ZK服务器");
                connectedSemaphore.countDown();
            }
            //创建节点
            else if (Watcher.Event.EventType.NodeCreated == eventType) {
                System.out.println(logPrefix + "节点创建");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.exists(path, true);
            }
            //更新节点
            else if (Watcher.Event.EventType.NodeDataChanged == eventType) {
                System.out.println(logPrefix + "节点数据更新");
                System.out.println("我看看走不走这里........");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // System.out.println(logPrefix + "数据内容: " + this.readData(PARENT_PATH, true));
            }
            //更新子节点
            else if (Watcher.Event.EventType.NodeChildrenChanged == eventType) {
                System.out.println(logPrefix + "子节点变更");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             //   System.out.println(logPrefix + "子节点列表：" + this.getChildren(PARENT_PATH, true));
            }
            //删除节点
            else if (Watcher.Event.EventType.NodeDeleted == eventType) {
                System.out.println(logPrefix + "节点 " + path + " 被删除");
            }
            else ;
        }
        else if (Watcher.Event.KeeperState.Disconnected == keeperState) {
            System.out.println(logPrefix + "与ZK服务器断开连接");
        }
        else if (Watcher.Event.KeeperState.AuthFailed == keeperState) {
            System.out.println(logPrefix + "权限检查失败");
        }
        else if (Watcher.Event.KeeperState.Expired == keeperState) {
            System.out.println(logPrefix + "会话失效");
        }
        else ;

        System.out.println("--------------------------------------------");

    }
}
