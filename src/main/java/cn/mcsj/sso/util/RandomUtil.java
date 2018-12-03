package cn.mcsj.sso.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @Description: 获取随机数工具类
 * @author admin
 * @date 2018年11月20日 下午4:10:00
 *
 */
public final class RandomUtil {

	public static String[] codeSequence = new String[] { "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D",
			"E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
			"Z" };

	/**
	 * 获取指定长度的随机数
	 * @param length 获取随机数的长度
	 * @return
	 */
	public static String getCode(int length) {
		List<String> list = Arrays.asList(codeSequence);
		//洗牌操作
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(0, length);
		return result;
	}
}
