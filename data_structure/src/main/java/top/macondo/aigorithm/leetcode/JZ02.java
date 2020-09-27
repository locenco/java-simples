package top.macondo.aigorithm.leetcode;
/**
 * 题目描述
 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * @author: zhangchong
 * @Date: 2020/9/24 14:41
 **/
public class JZ02 {
	public String replaceSpace(StringBuffer str) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0, length = str.length(); i < length; i++) {
			char a = str.charAt(i);
			if(a == ' '){
				stringBuffer.append("%20");
			}else {
				stringBuffer.append(a);
			}
		}
		return stringBuffer.toString();
	}
}