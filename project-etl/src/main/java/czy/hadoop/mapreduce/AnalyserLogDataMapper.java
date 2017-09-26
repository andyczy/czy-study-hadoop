package czy.hadoop.mapreduce;

import czy.hadoop.common.Constant;
import czy.hadoop.common.EventLogConstants;
import czy.hadoop.utils.log.LoggerUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.zip.CRC32;

/**
 * @Description: 自定义数据解析map类
 * @Auther 陈郑游
 * @Data 2017/9/8 0008
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */

public class AnalyserLogDataMapper extends Mapper<Object, Text, NullWritable, Put> {

    private final Logger logger = Logger.getLogger(AnalyserLogDataMapper.class);
    // 主要用于标志，方便查看过滤数据
    private int inputRecords, filterRecords, outputRecords;
    private byte[] family = Bytes.toBytes(Constant.EVENT_LOGS_FAMILY_NAME);
    private CRC32 crc32 = new CRC32();

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        this.inputRecords++;
        this.logger.debug("Analyse data of -- debug:" + value);

        try {
            // 解析日志
            Map<String, String> clientInfo = LoggerUtil.handleLog(value.toString());

            // 过滤解析失败的数据
            if (clientInfo.isEmpty()) {
                this.filterRecords++;
                return;
            }

            // 获取事件名称
            String eventAliasName = clientInfo.get(Constant.LOG_COLUMN_NAME_EVENT_NAME);
            EventLogConstants.EventEnum event = EventLogConstants.EventEnum.valueOfAlias(eventAliasName);
            switch (event) {     // 判断数据
            case LAUNCH:
            case PAGEVIEW:
            case CHARGEREQUEST:
            case CHARGEREFUND:
            case CHARGESUCCESS:
            case EVENT:
                this.handleData(clientInfo, event, context);    // 处理数据
                break;
            default:
                this.filterRecords++;
                this.logger.warn("该事件没法进行解析，事件名称为:" + eventAliasName);
            }
        } catch (Exception e) {
            this.filterRecords++;
            this.logger.error("处理数据发出异常，数据:" + value, e);
        }
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        super.cleanup(context);
        logger.info("输入数据:" + this.inputRecords + "；输出数据:" + this.outputRecords + "；过滤数据:" + this.filterRecords);
    }

    /**
     * 具体处理数据的方法
     * 
     * @param clientInfo
     * @param context
     * @param event
     * @throws InterruptedException
     * @throws IOException
     */
    private void handleData(Map<String, String> clientInfo, EventLogConstants.EventEnum event, Context context)
            throws IOException, InterruptedException {

        String uuid = clientInfo.get(Constant.LOG_COLUMN_NAME_UUID);
        String memberId = clientInfo.get(Constant.LOG_COLUMN_NAME_MEMBER_ID);
        String serverTime = clientInfo.get(Constant.LOG_COLUMN_NAME_SERVER_TIME);

        if (StringUtils.isNotBlank(serverTime)) {                   // 要求服务器时间不为空
            clientInfo.remove(Constant.LOG_COLUMN_NAME_USER_AGENT); // 浏览器信息去掉
            // timestamp  +  (uuid+memberid+event).crc
            String rowkey = this.generateRowKey(uuid, memberId, event.alias, serverTime);

            Put put = new Put(Bytes.toBytes(rowkey));
            for (Map.Entry<String, String> entry : clientInfo.entrySet()) {
                if (StringUtils.isNotBlank(entry.getKey()) && StringUtils.isNotBlank(entry.getValue())) {
                    put.add(family, Bytes.toBytes(entry.getKey()), Bytes.toBytes(entry.getValue()));
                }
            }
            context.write(NullWritable.get(), put);
            this.outputRecords++;
        } else {
            this.filterRecords++;
        }
    }

    /**
     * 根据uuid memberid servertime创建rowkey
     * (uuid+memberid+event).crc
     * @param uuid
     * @param memberId
     * @param eventAliasName
     * @param serverTime
     * @return
     */
    private String generateRowKey(String uuid, String memberId, String eventAliasName, String serverTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(serverTime).append("_");  //时间戳
        this.crc32.reset();

        if (StringUtils.isNotBlank(uuid)) {
            this.crc32.update(uuid.getBytes());
        }
        if (StringUtils.isNotBlank(memberId)) {
            this.crc32.update(memberId.getBytes());
        }

        this.crc32.update(eventAliasName.getBytes());
        sb.append(this.crc32.getValue() % 100000000L);
        return sb.toString();
    }
}
