package top.fan2wan.oauth.test;

import com.google.common.hash.Hashing;
import org.apache.commons.lang.StringUtils;

/**
 * @Author: fanT
 * @Date: 2021/2/23 9:37
 * @Description: test for bitMap
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
