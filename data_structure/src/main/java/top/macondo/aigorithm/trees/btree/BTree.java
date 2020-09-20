package top.macondo.aigorithm.trees.btree;

/**
 * @author: zhangchong
 * @Date: 2020/9/15 11:44
 **/
public class BTree
{
	/**
	 * B树的阶
	 */
	private int m;
	/**
	 * 最大
	 */
	private int MAX = m -1;
	private int MIN= (int)Math.ceil(m / 2) -1;

	public BTree(int m) {
		this.m = m;
	}

	int dataCount;
	int[] data = new int[m + 1];
	int childCount;
	BTree[] subTree = new BTree[m + 2];


}
