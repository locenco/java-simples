package top.macondo.aigorithm.leetcode;

/**
 * zhangchong
 * 2020/1/19 10:39
 **/
public class L2_ReplaceSpace {
	public String replaceSpace(StringBuffer str) {
		StringBuffer result = new StringBuffer();
		int length = str.length();
		int pos = 0;
		while (pos < length) {
			char temp = str.charAt(pos);
			if (' ' == temp) {
				result.append("%20");
			} else {
				result.append(temp);
			}
			pos++;
		}
		return result.toString();
	}

	public String replaceSpace2(StringBuffer str) {
		int P1 = str.length() - 1;
		for (int i = 0; i <= P1; i++)
			if (str.charAt(i) == ' ')
				str.append("  ");

		int P2 = str.length() - 1;
		while (P1 >= 0 && P2 > P1) {
			char c = str.charAt(P1--);
			if (c == ' ') {
				str.setCharAt(P2--, '0');
				str.setCharAt(P2--, '2');
				str.setCharAt(P2--, '%');
			} else {
				str.setCharAt(P2--, c);
			}
		}
		return str.toString();
	}
}
