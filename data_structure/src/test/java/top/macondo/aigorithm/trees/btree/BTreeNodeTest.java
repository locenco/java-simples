package top.macondo.aigorithm.trees.btree;


import org.junit.Test;

/**
 * @author: zhangchong
 * @Date: 2020/9/16 14:44
 **/
public class BTreeNodeTest {

	@Test
	public void printBTreeNode() {
		BTreeNode bTreeNode = new BTreeNode(3);
		for (int i = 0; i < 50; i++) {
			bTreeNode = bTreeNode.insert(i);
		}
		BTreeNode bTreeNode1 = bTreeNode.search(20);
		BTreeNode bTreeNode2 = bTreeNode.search(200);
		bTreeNode.delete(20);
		bTreeNode.print();
	}
}