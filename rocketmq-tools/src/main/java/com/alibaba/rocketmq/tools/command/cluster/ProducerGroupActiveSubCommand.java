package com.alibaba.rocketmq.tools.command.cluster;

import com.alibaba.rocketmq.common.protocol.body.ClusterInfo;
import com.alibaba.rocketmq.common.protocol.body.ProducerGroup;
import com.alibaba.rocketmq.common.protocol.body.ProducerGroupRunningInfo;
import com.alibaba.rocketmq.common.protocol.route.BrokerData;
import com.alibaba.rocketmq.remoting.RPCHook;
import com.alibaba.rocketmq.tools.admin.DefaultMQAdminExt;
import com.alibaba.rocketmq.tools.command.SubCommand;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.util.Map;
import java.util.Set;

/**
 * 获取当前所有活动的ProducerGroup信息
 * Created by geewu on 2015/1/7.
 */
public class ProducerGroupActiveSubCommand implements SubCommand {


    @Override
    public String commandName() {
        return "productGroupList";
    }

    @Override
    public String commandDesc() {
        return "List of acitve producerGroup";
    }

    @Override
    public Options buildCommandlineOptions(Options options) {

        return options;
    }

    @Override
    public void execute(CommandLine commandLine, Options options, RPCHook rpcHook) {

        DefaultMQAdminExt defaultMQAdminExt = new DefaultMQAdminExt(rpcHook);

        defaultMQAdminExt.setInstanceName(Long.toString(System.currentTimeMillis()));


        try {
            defaultMQAdminExt.start();

            ClusterInfo clusterInfoSerializeWrapper = defaultMQAdminExt.examineBrokerClusterInfo();


            System.out.printf("%-16s  %-32s %-32s %14s %14s\n",//
                    "#Cluster Name",//
                    "#Broker Name",//
                    "#Broker Ip",
                    "#ProducerGroup",//
                    "#ConnectionCount"//

            );

            for (Map.Entry<String, Set<String>> cluster : clusterInfoSerializeWrapper.getClusterAddrTable().entrySet()) {

                String clusterName = cluster.getKey();
                for (String brokerName : cluster.getValue()) {
                    BrokerData brokerData = clusterInfoSerializeWrapper.getBrokerAddrTable().get(brokerName);
                    ProducerGroup producerGroup = defaultMQAdminExt.examineActiveProducerGroupInfo(brokerData.selectBrokerAddr());

                    for (ProducerGroupRunningInfo pGroup : producerGroup.getGroupRunningInfos()) {
                        System.out.printf("%-16s  %-32s %-32s %14s %14s\n",//
                                clusterName,//
                                brokerName,//
                                brokerData.selectBrokerAddr(),
                                pGroup.getProducerGroup(),//
                                Integer.toString(pGroup.getClientCount())

                        );
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            defaultMQAdminExt.shutdown();
        }
    }
}
