package top.fan2wan.web.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import top.fan2wan.api.dto.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: fanT
 * @Date: 2021/3/2 14:37
 * @Description: util for http
 */
public class HttpUtil {

    /**
     * 输出指定的result
     *
     * @param response RES
     * @param r        result
     */
    public static void out(HttpServletResponse response, Result r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
