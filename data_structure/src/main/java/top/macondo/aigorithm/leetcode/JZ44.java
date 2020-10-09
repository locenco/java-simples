package top.macondo.aigorithm.leetcode;

/**
 * 牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
 * @author: zhangchong
 * @Date: 2020/10/8 15:53
 **/
public class JZ44 {
	public static void main(String[] args) {
		JZ44 jz44 = new JZ44();
		jz44.ReverseSentence("student. a am I");
	}
	public String ReverseSentence(String str) {
		StringBuilder sb = new StringBuilder();
		int start = -1;
		int end = str.length();
		for (int i = end - 1; i >= 0; i--) {
			if(str.charAt(i) == ' ' || i == 0){
				if (i == 0) {
					start = i;
				}else {
					start = i +1;
				}
				sb.append(str.substring(start, end));
				end = i;
				if (i != 0) {
					sb.append(" ");
				}
			}
		}
		if (start == -1) {
			return str;
		}
		return sb.toString();
	}
}
