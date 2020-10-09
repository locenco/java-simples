package top.macondo.aigorithm.leetcode;

/**
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 * @author: zhangchong
 * @Date: 2020/10/8 15:25
 **/
public class JZ49 {
	public static void main(String[] args) {
		JZ49 jz49 = new JZ49();
		System.out.println(jz49.StrToInt("+123"));
		System.out.println(jz49.StrToInt("+1a23"));
		System.out.println(jz49.StrToInt("-13"));
		System.out.println(jz49.StrToInt("13"));
		System.out.println(		Integer.parseInt("13"));
	}
	public int StrToInt(String str) {
		if (str == null || "".equals(str)) {
			return 0;
		}
		int symbol = 1;
		int sum = 0;
		for (int i = 0; i < str.length(); i++) {
		    char a = str.charAt(i);
		    if (i == 0){
		    	if(a == '+'){
		    		symbol = 1;
		    		continue;
				}
				if (a == '-') {
					symbol = -1;
					continue;
				}
			}
			if (a >= '0' && a <= '9') {
				sum = sum * 10 + a - '0';
			}else {
				return 0;
			}
		}
		return sum * symbol;
	}
}
