package com.alibaba.rocketmq.common.protocol.body;

/**
 * 生产组运行状态
 * Created by geewu on 2015/1/7.
 */
public class ProducerGroupRunningInfo  {
    private String producerGroup;
    private int clientCount;

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public int getClientCount() {
        return clientCount;
    }

    public void setClientCount(int clientCount) {
        this.clientCount = clientCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProducerGroupRunningInfo that = (ProducerGroupRunningInfo) o;

        if (clientCount != that.clientCount) return false;
        if (producerGroup != null ? !producerGroup.equals(that.producerGroup) : that.producerGroup != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = producerGroup != null ? producerGroup.hashCode() : 0;
        result = 31 * result + clientCount;
        return result;
    }
}
