package wsj.structure.binarytree;

import javax.jws.WebParam.Mode;

/**
 * ������.
 * @author WSJ
 *
 */
public class BinaryTree {

	
	/**
	 * ���ڵ�.
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
		System.out.println("���� : 88 = " + binaryTree.contains(88));
		System.out.println("���� : 66 = " + binaryTree.contains(66));
		
		
		
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
	 * ��������.���Ҷ�����.
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
	 * �������
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
	 * �������������ڵ�.
	 * @param data : ���ڵ�����.
	 * @return ����������Ѿ�����, ����false, ���򷵻�true.
	 */
	public boolean createTree(int data) {
		if (mRoot != null) {
			return false;
		}
		mRoot = new TreeNode(data);
		return true;
	}
	
	/**
	 * @return �����Ϊ���򷵻�true.
	 */
	public boolean isEmpty() {
		return mRoot == null;
	}
	
	/**
	 * ��������.��ȡԪ�ؽڵ�.
	 * @param data ����.
	 * @return ������򷵻ؽڵ�,���򷵻�null
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
	 * �Ƿ����ָ����Ԫ��.
	 * @param data
	 * @return
	 */
	public boolean contains(int data) {
		return get(data) != null;
	}
	
	/**
	 * ɾ��Ԫ��.
	 * ɾ��Ԫ����Ҫע��һ�¼���:
	 * 		1. ���û�к���ֱ��ɾ��.
	 * 		2. ֻ������/�Һ���, ֱ���ú��ӽڵ㶥���ֽڵ�λ��
	 * 		3. ���Һ��Ӷ���
	 * 			3.1  ������λ. 
	 * 			3.2  ���ӵ�����������. ɾ��Ԫ�ص���������Ϊ���ӵ�������.
	 * 			3.3  ���ӵ���������ӵ� , ɾ��Ԫ�ص���������.(СԪ��)
	 * 			 
	 * @param data ����.
	 * @return ɾ����Ԫ��. ���� null
	 */
	public TreeNode delete(int data) {
		// ��ȡ����Ԫ��.
		TreeNode node = get(data);
		TreeNode parent = node.parent;
		// 1. û�к���ֱ��ɾ��.
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
			// 2. ������ 
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
			// 3. �Һ���
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
			// 4. ������
			if (node.leftChild.rightChild != null) {
				System.out.println("11111");
				// 4.1�������ӵ�������λ��. �� ɾ���ڵ���������е���Сλ��.
				TreeNode minNode = getMinimum(node.rightChild);
				node.leftChild.rightChild.parent = minNode;
				minNode.leftChild = node.leftChild.rightChild;
			}
			// 4.2 ����ɾ���ڵ���Һ��ӵ�, ���ӵ��Һ��Ӵ�
			node.leftChild.rightChild = node.rightChild;
			node.rightChild.parent = node.leftChild;
			
			// 4.3  ɾ����ǰ�ڵ�
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
	 * ��ȡ�����е���Сֵ.
	 * @return
	 */
	private TreeNode getMinimum(TreeNode root) {
		if (root.leftChild == null) {
			return root;
		}
		return getMinimum(root.leftChild);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
