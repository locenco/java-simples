package top.macondo.aigorithm.trees.avl;

/**
 * @author: zhangchong
 * @Date: 2020/9/11 15:14
 **/
public class Node {
	private int key;
	private int height;
	private Node left;
	private Node right;

	public Node(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}
}
