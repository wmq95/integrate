package top.fan2wan.test.controller;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;
import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.test.config.MqConfig;
import top.fan2wan.test.dto.IUser;
import top.fan2wan.test.dto.UserDTO;
import top.fan2wan.test.entity.EsUser;
import top.fan2wan.test.entity.User;
import top.fan2wan.test.feign.TestFeignApi;
import top.fan2wan.test.mq.Sender;
import top.fan2wan.test.util.RedisUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @Author: fant2
 * @Date: 2020/9/28 16:22
 * @Description: teset controller
 */
@RestController
@ApiIgnore
public class TestController implements TestFeignApi {

    private final DozerBeanMapper mapper;
    private final RedisUtil redisUtil;
    private final RabbitTemplate rabbitTemplate;
    private final Sender sender;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    public TestController(DozerBeanMapper mapper, RedisUtil redisUtil,
                          RabbitTemplate rabbitTemplate, Sender sender,
                          ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.mapper = mapper;
        this.redisUtil = redisUtil;
        this.rabbitTemplate = rabbitTemplate;
        this.sender = sender;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    /**
     * test for service
     *
     * @param name name
     * @return name.toLower
     */
    @Override
    public String toLower(String name) {
        return name.toLowerCase();
    }

    /**
     * test for feign
     *
     * @param name name
     * @return name.toUpper
     */
    @Override
    public String testForName(String name) {
        logger.info("testForName -- name was :{}", name);
        logger.info("wait for 8s");
        //测试feign调用超时  超市时间为6s
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name.toUpperCase();
    }

    /**
     * test for future
     *
     * @param name name
     * @return future<String>
     */
    @Override
    public FutureTask<String> testForFuture(String name) {
        logger.info("testForFuture -- name was :{}", name);
        logger.info("wait for 2s");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new FutureTask<>(name::toUpperCase);
    }

    /**
     * testForCallable
     *
     * @param name name
     * @return callable
     */
    @Override
    public Callable<String> testForCallable(String name) {
        logger.info("testForCallable -- name was :{}", name);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name::toUpperCase;
    }

    /**
     * testForRequestHolder
     *
     * @param name name
     * @return String
     */
    @Override
    public String testForRequestHolder(String name) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String user = requestAttributes.getRequest().getHeader("user");
        logger.info("testForRequestHolder -- user was :{}", user);
        return user;
    }

    @RequestMapping("testService/index/dozer")
    public IUser dozerTest() {

        User user = new User();
        user.setId(IdGenerator.getId());
        user.setName("fant2");
        user.setPassword("123456");
        logger.info("dozer....success");
        return mapper.map(user, UserDTO.class);
    }

    @RequestMapping("testService/index/redis/set")
    public Boolean set() {
        return redisUtil.set("fant2", new User().setName("fant2").setId(IdGenerator.getId()));
    }

    @RequestMapping("testService/index/redis/get")
    public Object get() {
        return redisUtil.get("fant2");
    }

    @RequestMapping("testService/index/redis/getUser")
    public User getUser() {
        //会报错 因为value 序列化设置问题
        return (User) redisUtil.get("fant2");
    }

    @RequestMapping("testService/index/redis/getUserByDozer")
    public User getUserByDozer() {
        return mapper.map(redisUtil.get("fant2"), User.class);
    }

    @RequestMapping("testService/index/mq/send")
    public Boolean send() {

        rabbitTemplate.convertAndSend(MqConfig.QUEUE_NAME, "fant");
        return true;
    }

    @RequestMapping("testService/index/mq/fanout")
    public Boolean fanoutSend() {

        return sender.sendFanoutMsg();
    }

    @RequestMapping("testService/index/mq/topic")
    public Boolean topicSend() {

        return sender.sendTopicMsg();
    }

    @RequestMapping("testService/index/es/save")
    public Long save() {
        User user = new User().setName("fant2").setId(IdGenerator.getId());
//        EsUser save = esRepository.save(mapper.map(user, EsUser.class));
        EsUser save = elasticsearchRestTemplate.save(mapper.map(user, EsUser.class));
        return save.getId();
    }

    @RequestMapping("testService/index/es/get")
    public EsUser get(@RequestParam(value = "id") Long id) {

        return elasticsearchRestTemplate.get(id.toString(), EsUser.class);
    }

    @RequestMapping("testService/index/es/update")
    public Long update(@RequestParam(value = "id") Long id) {
        User user = new User().setId(id)
                .setNickname("update")
                .setUserName(id.toString());
        EsUser save = elasticsearchRestTemplate.save(mapper.map(user, EsUser.class));
        return save.getId();
    }

    @RequestMapping("testService/index")
    public Boolean index() {
        return true;
    }
}
