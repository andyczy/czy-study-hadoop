package czy.hadoop.utils.userAgent;

import java.io.IOException;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;


 /**
 * @Auther 陈郑游
 * @Data 2017/9/26 0026
 * @Description: 解析浏览器的user agent的工具类，内部就是调用这个uasparser jar文件
 * @CSDN:http://blog.csdn.net/javawebrookie
 * @GITHUB:https://github.com/AndyCZY
 */
public class UserAgentUtil {
	static UASparser uasParser = null;

	// static 代码块, 初始化uasParser对象
	static {
		try {
			uasParser = new UASparser(OnlineUpdater.getVendoredInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析浏览器的user agent字符串，返回UserAgentInfo对象。<br/>
	 * 如果user agent为空，返回null。如果解析失败，也直接返回null。
	 * 
	 * @param userAgent
	 *            要解析的user agent字符串
	 * @return 返回具体的值
	 */
	public static UserAgentInfo analyticUserAgent(String userAgent) {
		UserAgentInfo result = null;
		if (!(userAgent == null || userAgent.trim().isEmpty())) {
			// 此时userAgent不为null，而且不是由全部空格组成的
			try {
				cz.mallat.uasparser.UserAgentInfo info = null;
				info = uasParser.parse(userAgent);
				result = new UserAgentInfo();
				result.setBrowserName(info.getUaFamily());
				result.setBrowserVersion(info.getBrowserVersionInfo());
				result.setOsName(info.getOsFamily());
				result.setOsVersion(info.getOsName());
			} catch (IOException e) {
				// 出现异常，将返回值设置为null
				result = null;
			}
		}
		return result;
	}


}
