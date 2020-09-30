package top.fan2wan.web.config;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.fan2wan.api.dto.Result;
import top.fan2wan.api.exception.BusinessException;
import top.fan2wan.api.exception.MsgCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: fanT
 * @Date: 2020/9/30 14:43
 * @Description: AbstractExceptionHandle
 */
public abstract class AbstractExceptionHandle {

    /**
     * 异常状态
     */
    private static final int HTTP_CODE_500 = 500;

    /**
     * 方法参数异常
     */
    private static final int HTTP_METHOD_ARGUMENT_NOT_VALID = 5001;

    /**
     * 成功状态
     */
    private static final int HTTP_CODE_200 = 200;

    /**
     * The constant DEFAULT_MESSAGE.
     */
    private static final String DEFAULT_MESSAGE = "default_message";
    public static final String DUPLICATE_ENTRY = "Duplicate entry";
    private static Logger log = LoggerFactory.getLogger(AbstractExceptionHandle.class);

    /**
     * 异常处理
     *
     * @param request
     * @param response
     * @param ex
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Result resultMsg = new Result();
        resultMsg.setTimestamp(System.currentTimeMillis());
        if (ex instanceof BusinessException) {
            BusinessException baseException = (BusinessException) ex;
            resultMsg.setCode(baseException.getCode());
            resultMsg.setMessage(baseException.getMessage());
            log.error(baseException.getMessage(), ex);
        } else if (ex instanceof MethodArgumentNotValidException) {
            resultMsg.setCode(HTTP_CODE_500);
            int index = ex.getMessage().lastIndexOf(DEFAULT_MESSAGE);
            if (index > 0) {
                String defaultMessage = ex.getMessage().substring(index + DEFAULT_MESSAGE.length(), ex.getMessage().length() - 2);
                resultMsg.setMessage("参数异常" + defaultMessage);
            } else {
                StringBuilder msgBuilder = new StringBuilder();
                for (ObjectError objectError : ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors()) {
                    if (StrUtil.isNotEmpty(objectError.getDefaultMessage())) {
                        msgBuilder.append(objectError.getDefaultMessage()).append(";");
                    }
                }
                resultMsg.setMessage(StrUtil.isNotEmpty(msgBuilder.toString()) ? msgBuilder.toString() : "参数异常");
            }
            log.error(ex.getMessage(), ex);
        } else {
            resultMsg.setCode(HTTP_CODE_500);
            resultMsg.setMessage(MsgCode.FAILED.getMessage());
            if (ex.getMessage() != null && ex.getMessage().contains(DUPLICATE_ENTRY)) {
                resultMsg.setMessage(ex.getCause().getMessage());
            }
            ex.printStackTrace();
            log.error(ex.getMessage(), ex);
        }
        return resultMsg;
    }
}
