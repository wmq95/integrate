package top.fan2wan.common.util.bloom;

import com.google.common.hash.Hashing;

/**
 * @Author: fanT
 * @Date: 2020/10/12 9:44
 * @Description: test for bloom
 *
 * 存在问题
 * 数组越界
 * 余数 出现-1 的状态
 * 粗暴解决方案 abs 取绝对值
 */
public class BloomTest {

//    int[] seed = new int[];

    final static int ARRAY_LENGTH = 512;
    final static int HASH_COUNT = 5;
    final static int EXIST_INT_FLAG = 1;
    byte[] bloomArray = new byte[ARRAY_LENGTH];


    public boolean add(String str) {
        if (exist(str)) {
            return false;
        } else {
            byte[] bytes = str.getBytes();
            for (int i = 1; i < HASH_COUNT + 1; i++) {
                int temp = (Hashing.murmur3_32(i).hashBytes(bytes).asInt()) % ARRAY_LENGTH;
                temp = Math.abs(temp);
                bloomArray[temp] = EXIST_INT_FLAG;
            }
            return true;
        }
    }

    public boolean exist(String str) {
        boolean existFlag = true;
        byte[] bytes = str.getBytes();
        for (int i = 1; i < HASH_COUNT + 1; i++) {
            int temp = (Hashing.murmur3_32(i).hashBytes(bytes).asInt()) % ARRAY_LENGTH;
            temp = Math.abs(temp);
            existFlag = existFlag && (EXIST_INT_FLAG == bloomArray[temp]);
        }
        return existFlag;
    }

    public static void main(String[] args) {
        BloomTest test = new BloomTest();
        System.out.println(test.add("https://mp.weixin.qq.com/s?__biz=MzU2MDY0NDA1MQ==&mid=2247488422&idx=2&sn=778c44edea01dea02889696cbbf95512&chksm=fc05b91acb72300c8cd191917f8505b3b2095f49f91d95fc97036b1e0a70b02a2d87ceeb99c7&scene=126&sessionid=1602461995&key=83a3587a80db82bc031c087c2ff8fa226c4527fef2860c11fdc21890104640ff612be2a2b6ea769e6332d0869e12d3e930978312b40e68e07272da809c934940f37bc647cfa745446a4e8c83afd7a772e0507fa98461d3d43422053fd81424a91dfc465bc45f3f0d64edbc1fe277570abf5b04509f6128c4faf4b10feabd9e70&ascene=1&uin=MTk0OTY0Mjg2MQ%3D%3D&devicetype=Windows+10+x64&version=63000016&lang=zh_CN&exportkey=AezJJztXEEanOUUSMgAXzkI%3D&pass_ticket=s4YXUG4jcYbZsFjNElPlQeeuGC9xsmfjoOomogXl8hKH2vw7s8bz3x9q2j2rmtUS&wx_header=0"));;
        System.out.println(test.exist("https://p.weixin.qq.com/s?__biz=MzU2MDY0NDA1MQ==&mid=2247488422&idx=2&sn=778c44edea01dea02889696cbbf95512&chksm=fc05b91acb72300c8cd191917f8505b3b2095f49f91d95fc97036b1e0a70b02a2d87ceeb99c7&scene=126&sessionid=1602461995&key=83a3587a80db82bc031c087c2ff8fa226c4527fef2860c11fdc21890104640ff612be2a2b6ea769e6332d0869e12d3e930978312b40e68e07272da809c934940f37bc647cfa745446a4e8c83afd7a772e0507fa98461d3d43422053fd81424a91dfc465bc45f3f0d64edbc1fe277570abf5b04509f6128c4faf4b10feabd9e70&ascene=1&uin=MTk0OTY0Mjg2MQ%3D%3D&devicetype=Windows+10+x64&version=63000016&lang=zh_CN&exportkey=AezJJztXEEanOUUSMgAXzkI%3D&pass_ticket=s4YXUG4jcYbZsFjNElPlQeeuGC9xsmfjoOomogXl8hKH2vw7s8bz3x9q2j2rmtUS&wx_header=0"));
    }
}
