package czy.hadoop.utils.userAgent.test;


import czy.hadoop.utils.userAgent.UserAgentInfo_Model;
import czy.hadoop.utils.userAgent.UserAgent_Util;

public class UserAgentUtil_Main {
	public static void main(String[] args) {

		String userAgent1 = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.71 Safari/537.36";
		String userAgent2 = "Mozilla/5.0 (Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; GWX:QUALIFIED; rv:11.0) like Gecko";
		String userAgent3 = "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/7.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; GWX:QUALIFIED)";

		UserAgentInfo_Model info = UserAgent_Util.analyticUserAgent(userAgent1);
		System.out.println(info);
	}
}
