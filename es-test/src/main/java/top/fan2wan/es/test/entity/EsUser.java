package top.fan2wan.es.test.entity;

/**
 * @Author: fanT
 * @Date: 2021/9/1 9:21
 * @Description:
 */
public class EsUser {

    private String name;

    private String sex;

    private int age;

    public EsUser(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
