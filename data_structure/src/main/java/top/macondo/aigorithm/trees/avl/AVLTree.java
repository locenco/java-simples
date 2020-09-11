package top.macondo.aigorithm.trees.avl;

import com.sun.istack.internal.NotNull;

/**
 * @author: zhangchong
 * @Date: 2020/9/11 15:16
 **/
public class AVLTree {
	private Node root;

	/**
	 * 找出左右子树最大高度，加上1。即为当前树的高度
	 * @param node
	 */
	void updateHeight(Node node){
		node.setHeight(1 + Math.max(height(node.getLeft()),height(node.getRight())));
	}
	int height(Node node){
		return node == null ? -1 : node.getHeight();
	}
	int getBalance(Node node) {
		return (node == null) ? 0 : height(node.getRight()) - height(node.getLeft()) ;
	}

	/**
	 *
	 * @param y
	 * @return
	 */
	Node rotateRight(Node y){
		Node x = y.getLeft();
		Node z = x.getRight();
		x.setRight(y);
		y.setLeft(z);
		updateHeight(x);
		updateHeight(y);
		return x;
	}
	Node rotateLeft(Node y){
		Node x = y.getRight();
		Node z = x.getLeft();
		x.setLeft(y);
		y.setRight(z);
		updateHeight(x);
		updateHeight(y);
		return x;
	}
	Node reBalance(Node z){
		updateHeight(z);
		int balance = getBalance(z);
		if(balance > 1) {
			if(height(z.getRight().getRight()) > height(z.getRight().getLeft())){
				z = rotateLeft(z);
			}else {
				z.setRight(rotateRight(z.getRight()));
				z = rotateLeft(z);
			}
		}else if(balance < -1){
			if(height(z.getLeft().getLeft()) > height(z.getLeft().getRight())){
				z = rotateRight(z);
			}else {
				z.setLeft(rotateLeft(z.getLeft()));
				z = rotateRight(z);
			}
		}
		return z;
	}
	Node insert(Node node, int key){
		if (node == null){
			return new Node(key);
		}else if(node.getKey() > key) {
			node.setLeft(insert(node.getLeft(),key));
		}else if(node.getKey() < key){
			node.setRight(insert(node.getRight(),key));
		}else {
			throw new RuntimeException("duplicate key!");
		}
		return reBalance(node);
	}
	Node delete(Node node, int key){
		if (node == null){
			return node;
		}else if(node.getKey() > key) {
			node.setLeft(delete(node.getLeft(),key));
		}else if(node.getKey() < key){
			node.setRight(delete(node.getRight(),key));
		}else {
			if(node.getLeft() == null || node.getRight() == null){
				node = (node.getLeft() == null) ? node.getRight(): node.getLeft();
			}else {
				Node mostLeftChild = mostLeftChild(node.getRight());
				node.setKey(mostLeftChild.getKey());
				node.setRight(delete(node.getRight(),mostLeftChild.getKey()));
			}
		}
		if(node != null){
			node = reBalance(node);
		}
		return node;
	}
	Node mostLeftChild(@NotNull Node node){
		if (node.getLeft() == null){
			return node;
		}else {
			return mostLeftChild(node.getLeft());
		}
	}
	Node find(int key) {
		Node current = root;
		while (current != null){
			if(current.getKey() == key){
				break;
			}
			current = (current.getKey() > key) ? current.getLeft() : current.getRight();
		}
		return current;
	}
}
