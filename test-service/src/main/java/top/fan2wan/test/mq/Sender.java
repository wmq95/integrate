package top.fan2wan.test.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import top.fan2wan.test.config.MqConfig;

/**
 * @Author: fanT
 * @Date: 2020/12/7 9:32
 * @Description: sender of mq
 */
@Component
public class Sender {

    private final RabbitTemplate rabbitTemplate;

    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Boolean sendFanoutMsg() {
        rabbitTemplate.convertAndSend(MqConfig.EXCHANGE_FANOUT, "", "exchange_fanout");
        return true;
    }

    public Boolean sendTopicMsg() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                rabbitTemplate.convertAndSend(MqConfig.EXCHANGE_TOPIC, "topic.km.topic", "测试发布订阅模型：" + i);
            } else {
                rabbitTemplate.convertAndSend(MqConfig.EXCHANGE_TOPIC, "topic.km", "测试发布订阅模型：" + i);

            }
        }
        return true;
    }
}
