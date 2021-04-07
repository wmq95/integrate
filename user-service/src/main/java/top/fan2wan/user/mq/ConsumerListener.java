package top.fan2wan.user.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import top.fan2wan.user.entity.UserIntegral;
import top.fan2wan.user.service.IUserIntegralService;

import java.util.List;
import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2021/4/7 15:01
 * @Description: listener for consumer
 */
@Service
public class ConsumerListener implements MessageListenerConcurrently {

    @Autowired
    private IUserIntegralService userIntegralService;
    @Autowired
    private ObjectMapper mapper;

    /**
     * It is not recommend to throw exception,rather than returning ConsumeConcurrentlyStatus.RECONSUME_LATER if
     * consumption failure
     *
     * @param msgs    msgs.size() >= 1<br> DefaultMQPushConsumer.consumeMessageBatchMaxSize=1,you can modify here
     * @param context
     * @return The consume status
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        // 消息为空
        if (CollectionUtils.isEmpty(msgs)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt msg = msgs.get(0);
        if (Objects.isNull(msg)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }


        try {
            String body = new String(msg.getBody(), "utf-8");

            UserIntegral dto = mapper.readValue(body, UserIntegral.class);

//            System.out.println(userIntegralService.list().size());
            userIntegralService.addIntegral(dto.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
