package top.fan2wan.oauth.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: fanT
 * @Date: 2021/3/18 14:05
 * @Description: test for bcrypt
 */
public class BcryptTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);

        String password = "123456";

        String hashedPassword = encoder.encode(password);

        System.out.println("经过bcrypt 之后是:" + hashedPassword);
        System.out.println("是否能正确解密:" + encoder.matches(password, hashedPassword));

        System.out.println("=======================");
        hashedPassword = encoder.encode(password);
        System.out.println("第二次经过bcrypt 之后是:" + hashedPassword);
        System.out.println("是否能正确解密:" + encoder.matches(password, hashedPassword));
    }
}
