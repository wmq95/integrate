package top.fan2wan.api.dto;

import top.fan2wan.api.exception.BusinessException;
import top.fan2wan.api.support.IMsgCode;

import java.time.Instant;

/**
 * @Author: fanT
 * @Date: 2020/9/30 14:46
 * @Description: result
 */
public class Result<T> {
    /**
     * 成功code
     */
    public static int SUCCESS_CODE = 200;

    /**
     * 成功信息
     */
    public static String SUCCESS_MSG = "success";

    /**
     * 结果code
     */
    protected int code;

    /**
     * 结果提示信息
     */
    protected String message;

    /**
     * 结果对象
     */
    protected T result;

    /**
     * 时间戳
     */
    protected Long timestamp;

    /**
     * Instantiates a new Result.
     *
     * @param code the code
     */
    public Result(int code) {
        this.code = code;
    }

    /**
     * 带有check exception的获取结果方法.
     *
     * @return the t
     */
    public T result() {
        if (this.code != SUCCESS_CODE) {
            throw new BusinessException(this.code, this.message);
        }
        return this.result;
    }

    /**
     * Gets result code.
     *
     * @return the result code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets result code.
     *
     * @param code the result code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets result message.
     *
     * @return the result message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets result message.
     *
     * @param message the result message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * feigin 远程调用严禁使用此方法，请转用result()方法
     *
     * @return the result object
     */
    public T getResult() {
        return result;
    }

    /**
     * Sets result object.
     *
     * @param result the result object
     */
    public void setResult(T result) {
        this.result = result;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Instantiates a new Rst msg.
     *
     * @param msgCode the msg code
     * @param result  the result
     */
    public Result(IMsgCode msgCode, T result) {
        this.code = msgCode.getCode();
        this.message = msgCode.getMessage();
        this.result = result;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Instantiates a new Result.
     *
     * @param result the result
     */
    public Result(T result) {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MSG;
        this.result = result;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Instantiates a new Rst msg.
     *
     * @param msgCode the msg code
     */
    public Result(IMsgCode msgCode) {
        this.code = msgCode.getCode();
        this.message = msgCode.getMessage();
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * Instantiates a new Rst msg.
     */
    public Result() {
    }

    /**
     * Ok result.
     *
     * @return the result
     */
    public static Result ok() {
        return ok(null);
    }

    /**
     * Success result.
     *
     * @param data the data
     * @return the result
     */
    public static Result ok(Object data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MSG);
        result.setResult(data);
        result.setTimestamp(Instant.now().toEpochMilli());
        return result;
    }
}
