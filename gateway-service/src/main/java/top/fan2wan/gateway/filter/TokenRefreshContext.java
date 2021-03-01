package top.fan2wan.gateway.filter;

import top.fan2wan.gateway.bo.AccessTokenBO;

/**
 * @Author: fanT
 * @Date: 2021/3/1 10:42
 * @Description: support for tokenRefresh
 */
public class TokenRefreshContext {

    private static ThreadLocal<AccessTokenBO> holder = new ThreadLocal<>();

    public static AccessTokenBO get() {
        return holder.get();
    }

    public static void set(AccessTokenBO bo) {
        holder.set(bo);
    }

    public  static void clear() {
        holder.remove();
    }
}
