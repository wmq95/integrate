package top.fan2wan.database.rocketmq.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Author: fanT
 * @Date: 2021/3/29 13:23
 * @Description: config for producer
 */
@ConfigurationProperties(prefix = "rocketmq.producer")
public abstract class AbstractProducerConfig {

    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractProducerConfig.class);

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public void setMaxMessageSize(Integer maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public void setSendMsgTimeOut(Integer sendMsgTimeOut) {
        this.sendMsgTimeOut = sendMsgTimeOut;
    }

    public void setRetryTimesWhenSendFailed(Integer retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
    }

    protected String groupName;
    protected String namesrvAddr;
    protected Integer maxMessageSize;
    protected Integer sendMsgTimeOut;
    protected Integer retryTimesWhenSendFailed;
}
