package top.fan2wan.order.test;

import top.fan2wan.common.util.IdGenerator;
import top.fan2wan.order.entity.UserOrder;

import java.io.*;
import java.time.LocalDateTime;

/**
 * @Author: fanT
 * @Date: 2021/4/7 9:15
 * @Description: test for serializable
 */
public class SerializableTest {

    public static void main(String[] args)  {

        UserOrder userOrder = testUserOrder();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("E:\\temp\\object2.txt")))) {
            oos.writeObject(userOrder);
            userOrder.setId(12312312L);
            oos.writeObject(userOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("E:\\temp\\object2.txt")))) {

            UserOrder userOrder1 = (UserOrder) ois.readObject();
            UserOrder userOrder2 = (UserOrder) ois.readObject();
            System.out.println(userOrder1 == userOrder2); // true
            System.out.println(userOrder1.getId());//577111701036736512
            System.out.println(userOrder2.getId());//577111701036736512

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        {

        }
    }

    public static UserOrder testUserOrder() {
        UserOrder userOrder = new UserOrder();
        userOrder.setGmtModified(LocalDateTime.now());
        userOrder.setGmtCreate(userOrder.getGmtModified());
        userOrder.setUserId(IdGenerator.getId());
        userOrder.setId(userOrder.getUserId());
        return userOrder;
    }
}
