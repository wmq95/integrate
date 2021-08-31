import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.*;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Description:
 * @Author: Answer  2020/9/28 16:37
 */
public class CanalClientTest {

    public static void main(String args[]) {
        // 创建链接
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                11111), "example", "", "");

        // 每次请求多少数据
        int batchSize = 1000;
        try {
            connector.connect();

            // 订阅消息主题，支持正则匹配
            connector.subscribe(".*\\..*");
            while (true) {
                boolean processFlag = false;
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据
                long batchId = message.getId();
                int size = message.getEntries().size();
                // 没有数据库的变化，就等一会再查询
                if (batchId == -1 || size == 0) {
//                    System.out.println("empty count");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                    }
                } else {
                    System.out.println("batchID : " + batchId + " size: + " + size);
                    // 数据库有变化，处理变化
                    processFlag = process(message.getEntries());
                    if (processFlag) {
                        connector.ack(batchId); // 处理成功，提交确认
                    } else {
                        connector.rollback(batchId); // 处理失败, 回滚数据，将从上一次地方再次获取数据
                    }
                }
            }
        } finally {
            connector.disconnect();
        }
    }

    public static boolean process(List<Entry> entrys) {
        for (Entry entry : entrys) {
            // 跳过事物
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN
                    || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }
            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }
            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================&gt; binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));
            // 行变化的类型，主要是 增 改 删
            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    // 可以推送到 Kafka
                    printColumn(rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    printColumn(rowData.getAfterColumnsList());
                } else {
                    System.out.println("-------&gt; before");
                    printColumn(rowData.getBeforeColumnsList());
                    System.out.println("-------&gt; after");
                    printColumn(rowData.getAfterColumnsList());
                }
            }
        }
        return true;
    }

    private static void printColumn(List<Column> columns) {
        for (Column column : columns) {
            System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
        }
    }
}
