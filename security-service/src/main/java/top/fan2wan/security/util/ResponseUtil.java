package top.fan2wan.security.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import top.fan2wan.api.dto.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: fanT
 * @Date: 2021/1/29 14:34
 * @Description: util for response
 */
public class ResponseUtil {

    public static void out(HttpServletResponse response, Result r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
