package top.macondo.aigorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 * @author: zhangchong
 * @Date: 2020/10/8 14:10
 **/
public class JZ54 {
	public static void main(String[] args) {
		JZ54 jz54 = new JZ54();
		jz54.Insert('g');
		jz54.Insert('o');
		jz54.Insert('o');
		jz54.Insert('g');
		jz54.Insert('l');
		jz54.Insert('e');
		System.out.println(jz54.FirstAppearingOnce());

	}
	List<Character> list = new ArrayList<>();
	//Insert one char from stringstream
	public void Insert(char ch)
	{
		if (list.contains(ch)) {
			list.remove(list.indexOf(ch));
		}else {
			list.add(ch);
		}
	}
	//return the first appearence once char in current stringstream
	public char FirstAppearingOnce()
	{
		return list.isEmpty() ? '#' : list.get(0);
	}
}
