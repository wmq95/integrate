package top.fan2wan.commom.util;

import java.util.Objects;

/**
 * @Author: fanT
 * @Date: 2020/9/29 15:53
 * @Description: id generator
 */
public class IdGenerator {

    /**
     * 默认数据中心id
     */
    private static final long DEFAULT_DATA_CENTER_ID = 2L;

    /**
     * 默认机器码id
     */
    private static final long DEFAULT_MACHINE_ID = 2L;

    /**
     * 实例
     */
    private static final SnowFlake SNOWFLAKE_INSTANCE = initSnowFlake();

    /**
     * init
     *
     * @return SnowFlake
     */
    private static SnowFlake initSnowFlake() {
        String dataCenterIdStr = System.getProperty("dataCenterId");
        String machineIdStr = System.getProperty("machineId");
        if (Objects.nonNull(dataCenterIdStr)
                && Objects.nonNull(machineIdStr)) {
            System.out.println("user customer");
            return new SnowFlake(Integer.valueOf(dataCenterIdStr), Integer.valueOf(machineIdStr));
        } else {
            return new SnowFlake(DEFAULT_DATA_CENTER_ID, DEFAULT_MACHINE_ID);
        }
    }

    /**
     * generatorId
     *
     * @return Long 雪花ID
     */
    public static Long getId() {
        return SNOWFLAKE_INSTANCE.nextId();
    }
}
