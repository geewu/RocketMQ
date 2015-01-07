package com.alibaba.rocketmq.common.protocol.body;

import com.alibaba.rocketmq.remoting.protocol.RemotingSerializable;

import java.util.HashSet;

/**获取生产者组内容
 * Created by geewu on 2015/1/7.
 */
public class ProducerGroup extends RemotingSerializable {
    private HashSet<ProducerGroupRunningInfo> groupRunningInfos = new HashSet<ProducerGroupRunningInfo>();

    public HashSet<ProducerGroupRunningInfo> getGroupRunningInfos() {
        return groupRunningInfos;
    }

    public void setGroupRunningInfos(HashSet<ProducerGroupRunningInfo> groupRunningInfos) {
        this.groupRunningInfos = groupRunningInfos;
    }


}
