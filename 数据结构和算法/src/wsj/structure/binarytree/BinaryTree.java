package wsj.structure.binarytree;

import javax.jws.WebParam.Mode;

/**
 * 二叉树.
 * @author WSJ
 *
 */
public class BinaryTree {

	
	/**
	 * 根节点.
	 */
	private TreeNode mRoot;
	
	
	public static void main(String[] args) {
		final int elementDatas[] = new int[] {50, 40, 30,44, 88, 33, 87, 16, 7, 77};
		
		BinaryTree binaryTree = new BinaryTree();
		
		for (int i : elementDatas) {
			binaryTree.put(i);
		}
		binaryTree.binaryTreeMid(binaryTree.mRoot);
		
		System.out.println();
		System.out.println("查找 : 88 = " + binaryTree.contains(88));
		System.out.println("查找 : 66 = " + binaryTree.contains(66));
		
		
		
		binaryTree.delete(30);
		binaryTree.binaryTreeMid(binaryTree.mRoot);
	}
	
	class TreeNode {
		int data;
		
		TreeNode leftChild;
		TreeNode rightChild;
		TreeNode parent;
		
		
		public TreeNode(int data) {
			this.data = data;
			leftChild = null;
			rightChild = null;
			parent = null;
		}
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public TreeNode getParent() {
			return parent;
		}
		public void setParent(TreeNode parent) {
			this.parent = parent;
		}
		
	}
	
	/**
	 * 插入数据.查找二叉树.
	 * @param data
	 * @return
	 */
	public TreeNode put(int data){
		
		if (isEmpty()) {
			createTree(data);
			return mRoot;
		}

		TreeNode node = mRoot;
		TreeNode parent = null;
		
		while(node != null){
			parent = node;
			if (data < node.data) {
				node = node.leftChild;
			}else if (data > node.data) {
				node = node.rightChild;
			} else {
				return node;
			}
		}
		TreeNode current = new TreeNode(data);
		current.parent = parent;
		if (data < parent.data) {
			parent.leftChild = current;
		}else {
			parent.rightChild = current;
		}
		return current;
	}
	
	/**
	 * 中序遍历
	 */
	public void binaryTreeMid(TreeNode root) {
		if (root == null) {
			return;
		}
		
		binaryTreeMid(root.leftChild);
		
		System.out.print(root.data);
		if (root.parent != null) {
			System.out.print("--" + root.parent.data);
		}else {
			System.out.print("--");
		}
		
		System.out.print(" ");
		
		
		binaryTreeMid(root.rightChild);
		
	}

	/**
	 * 创建二叉树根节点.
	 * @param data : 根节点数据.
	 * @return 如果根几点已经存在, 返回false, 否则返回true.
	 */
	public boolean createTree(int data) {
		if (mRoot != null) {
			return false;
		}
		mRoot = new TreeNode(data);
		return true;
	}
	
	/**
	 * @return 如果树为空则返回true.
	 */
	public boolean isEmpty() {
		return mRoot == null;
	}
	
	/**
	 * 根据数据.获取元素节点.
	 * @param data 数据.
	 * @return 如果有则返回节点,否则返回null
	 */
	public TreeNode get(int data) {
		if (mRoot == null) {
			return null;
		}
		TreeNode node = mRoot;
		while(node != null){
			if (data < node.data) {
				node = node.leftChild;
			}else if (data > node.data) {
				node = node.rightChild;
			}else {
				return node;
			}
		}
		return null;
	}
	
	/**
	 * 是否包含指定的元素.
	 * @param data
	 * @return
	 */
	public boolean contains(int data) {
		return get(data) != null;
	}
	
	/**
	 * 删除元素.
	 * 删除元素需要注意一下几点:
	 * 		1. 如果没有孩子直接删除.
	 * 		2. 只有左孩子/右孩子, 直接让孩子节点顶替字节的位置
	 * 		3. 左右孩子都有
	 * 			3.1  左孩子上位. 
	 * 			3.2  左孩子的左字数不动. 删除元素的右字数作为左孩子的右字数.
	 * 			3.3  左孩子的右字数添加到 , 删除元素的右子数中.(小元素)
	 * 			 
	 * @param data 数据.
	 * @return 删除的元素. 或者 null
	 */
	public TreeNode delete(int data) {
		// 获取到该元素.
		TreeNode node = get(data);
		TreeNode parent = node.parent;
		// 1. 没有孩子直接删除.
		if (node.leftChild == null && node.rightChild == null) {
			if (node == mRoot) {
				mRoot =null;
				return node;
			}
			node.parent = null;
			if (node.data < parent.data) {
				parent.leftChild = null;
			}else {
				parent.rightChild = null;
			}
		}else if(node.rightChild == null) {
			// 2. 有左孩子 
			if (mRoot == node) {
				mRoot = node.leftChild;
				return node;
			}
			if (node.data < parent.data) {
				parent.leftChild = node.leftChild;
			}else {
				parent.rightChild = node.leftChild;
			}
			node.leftChild.parent = parent;
		}else if (node.leftChild == null) {
			// 3. 右孩子
			if (mRoot == node) {
				mRoot = node.rightChild;
				return node;
			}
			if (node.data < parent.data) {
					parent.leftChild = node.rightChild;
			}else {
				parent.rightChild = node.rightChild;
			}
			node.rightChild.parent = parent;
		}else {
			// 4. 俩孩子
			if (node.leftChild.rightChild != null) {
				System.out.println("11111");
				// 4.1更新左孩子的右字数位置. 到 删除节点的右字数中的最小位置.
				TreeNode minNode = getMinimum(node.rightChild);
				node.leftChild.rightChild.parent = minNode;
				minNode.leftChild = node.leftChild.rightChild;
			}
			// 4.2 更新删除节点的右孩子到, 左孩子的右孩子处
			node.leftChild.rightChild = node.rightChild;
			node.rightChild.parent = node.leftChild;
			
			// 4.3  删除当前节点
			if (mRoot != node) {
				if (node.data < parent.data) {
					parent.leftChild = node.leftChild;
				}else {
					parent.rightChild = node.leftChild;
				}
			}else {
				mRoot = node.leftChild;
			}
			node.leftChild.parent = parent;
		}
		return node;
	}
	
	/**
	 * 获取子树中的最小值.
	 * @return
	 */
	private TreeNode getMinimum(TreeNode root) {
		if (root.leftChild == null) {
			return root;
		}
		return getMinimum(root.leftChild);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
