package top.fan2wan.security.config;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: fanT
 * @Date: 2021/1/29 14:16
 * @Description: encoder for password
 */
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return Hashing.sha256().newHasher().putString(charSequence, Charsets.UTF_8).hash().toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String encoder) {
        return StrUtil.equals(Hashing.sha256().newHasher().putString(charSequence, Charsets.UTF_8).hash().toString(),
                encoder);
    }
}
