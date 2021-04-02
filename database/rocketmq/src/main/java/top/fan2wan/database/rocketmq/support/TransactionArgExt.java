package top.fan2wan.database.rocketmq.support;

import java.io.Serializable;

/**
 * @Author: fanT
 * @Date: 2021/4/2 8:43
 * @Description: arg for transactionalMsg
 */
public class TransactionArgExt implements Serializable{

    private static final long serialVersionUID = 6893141246123673845L;
    /**
     * 消息事务对应的类型
     * 用这个去判断执行对应的本地事务
     */
    String type;

    /**
     * 对应的业务参数
     * JSON 格式 后面便于dozer 转换
     */
    String data;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TransactionArgExt{");
        sb.append("type='").append(type).append('\'');
        sb.append(", data='").append(data).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
