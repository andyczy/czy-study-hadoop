package czy.hadoop.common;



 /**
 * @Auther 陈郑游
 * @Data 2017/9/8 0008
 * @Description:
 *      定义日志收集客户端收集得到的用户数据参数的name名称<br/>
 *      以及event_logs这张hbase表的结构信息<br/>
 *      用户数据参数的name名称就是event_logs的列名
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class EventLogConstants {


    /**
     * 事件枚举类。指定事件的名称
     */
    public static enum EventEnum {

        LAUNCH(1, "launch event", "e_l"),                   // launch事件，表示第一次访问
        PAGEVIEW(2, "page view event", "e_pv"),             // 页面浏览事件
        CHARGEREQUEST(3, "charge request event", "e_crt"),  // 订单生产事件
        CHARGESUCCESS(4, "charge success event", "e_cs"),   // 订单成功支付事件
        CHARGEREFUND(5, "charge refund event", "e_cr"),     // 订单退款事件
        EVENT(6, "event duration event", "e_e");            // 事件

        public final int id;            // id 唯一标识
        public final String name;       // 名称
        public final String alias;      // 别名，用于数据收集的简写

        private EventEnum(int id, String name, String alias) {
            this.id = id;
            this.name = name;
            this.alias = alias;
        }

        /**
         * 获取匹配别名的event枚举对象，如果最终还是没有匹配的值，那么直接返回null。
         * 
         * @param alias
         * @return
         */
        public static EventEnum valueOfAlias(String alias) {
            for (EventEnum event : values()) {
                if (event.alias.equals(alias)) {
                    return event;
                }
            }
            return null;
        }
    }


}
