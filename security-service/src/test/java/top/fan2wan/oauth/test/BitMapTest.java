package top.fan2wan.oauth.test;

import com.google.common.hash.Hashing;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: fanT
 * @Date: 2021/2/23 9:37
 * @Description: test for bitMap
 * <p>
 * redis中也有bitMap
 * 统计日活跃用户: setBit key userId 1 限制 用户id 不能过长 int范围 但是现实中 id 都是用Long类型
 * <p>
 * 改善: 为每一个用户申请一个bitMap 长度位32
 * key 就是当月 2020-01-用户id bitMap 就能够用来记录一个月得登录情况
 * <p>
 * 如此 1亿得用户 一个月得登录情况 仅仅只需要1亿*32位 / 8 ->B / 1000/1024/1024 不足0.5个G
 * <p>
 * 使用redis 得bitMap 记录access_token 过期自动续租返回新的access_token 为了使得原先得access_token 失效
 * <p>
 * 记录失效得token 可以把access_token 做一个md5 取得int值 然后用setBit
 * 事情一个bitMap  保存所有得access_token
 */
public class BitMapTest {

    private int capacity;

    private byte[] bytes;

    private byte[] negativeBytes;

    public BitMapTest(int capacity) {
        this.capacity = capacity;
        this.bytes = new byte[(this.capacity >> 3) + 1];
        this.negativeBytes = new byte[(this.capacity >> 3) + 1];
    }

    public void add(int num) {
        if (num > -1) {
            // num/8得到byte[]的index
            int arrayIndex = num >> 3;
            // num%8得到在byte[index]的位置
            int position = num & 0x07;
            //正数 和0
            //将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了。
            this.bytes[arrayIndex] |= 1 << position;
        } else {
            int position = (Math.abs(num) % 8) - 1;
            int arrayIndex = Math.abs(num / 8);
            this.negativeBytes[arrayIndex] |= 1 << position;
        }

    }

    /**
     * 拿出对应的byte  和入参二进制做与运算
     *
     * @param num num
     * @return false 表示没有  true 表示存在
     */
    public boolean contains(int num) {
        if (num > -1) {

            int arrayIndex = num >> 3;
            int position = num & 0x07;
            return (this.bytes[arrayIndex] & (1 << position)) != 0;
        } else {
            int position = (Math.abs(num) % 8) - 1;
            int arrayIndex = Math.abs(num / 8);
            return (this.negativeBytes[arrayIndex] & (1 << position)) != 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("正数bits分布\n");
        for (int i = 0; i < this.bytes.length; i++) {
            sb.append("第" + (i + 1) + "个bit是:"
                    + StringUtils.leftPad(Integer.toBinaryString(this.bytes[i] & 0xff), 8, '0') + "\n");
        }
        sb.append("负数bits分布\n");
        for (int i = 0; i < this.negativeBytes.length; i++) {
            sb.append("第" + (i + 1) + "个bit是:"
                    + StringUtils.leftPad(Integer.toBinaryString(this.negativeBytes[i] & 0xff), 8, '0') + "\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BitMapTest test = new BitMapTest(Integer.MAX_VALUE);

        String a = "ewqedsadasdasdasdas";
        String b = "ewqedsadasdasdasdas";
        String c = "sdasdasdasda";

        int md5A = Hashing.md5().hashBytes(a.getBytes()).asInt();
        int md5B = Hashing.md5().hashBytes(b.getBytes()).asInt();
        int md5C = Hashing.md5().hashBytes(c.getBytes()).asInt();

        test.add(md5A);
//        test.add(md5C);
        System.out.println(test.contains(md5B));
        System.out.println(test.contains(md5C));
//        test.add(md5A);
//        test.add(-3);
//        test.add(3);
//        test.add(4);
//        test.add(-4);
//        System.out.println(test);
        System.out.println(test.contains(-5));
    }
}
