package wsj.structure.tree;

import java.util.Stack;
import java.util.concurrent.SynchronousQueue;

import javax.jws.WebParam.Mode;
import javax.naming.InitialContext;
import javax.swing.plaf.basic.BasicBorders.RolloverButtonBorder;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

public class BinaryTree {

	/**
	 * ���ݽڵ�.
	 * @author WSJ
	 *
	 * @param <T>
	 */
	public static class TreeNode<T> {
		
		private int index;
		
		private T data;
		private TreeNode<T> leftChild;
		private TreeNode<T> rightChild;
		
		public TreeNode(int index, T data) {
			this.index = index;
			this.data = data;
			leftChild = null;
			rightChild = null;
		}
		
		
	}
	
	
	private static TreeNode<String> root;
	
	/**
	 * ������
	 */
	public static void makeTree(){
		TreeNode<String> nodeA = new TreeNode<String>(0, "A");
		TreeNode<String> nodeB = new TreeNode<String>(0, "B");
		TreeNode<String> nodeC = new TreeNode<String>(0, "C");
		TreeNode<String> nodeD = new TreeNode<String>(0, "D");
		TreeNode<String> nodeE = new TreeNode<String>(0, "E");
		TreeNode<String> nodeF = new TreeNode<String>(0, "F");
		TreeNode<String> nodeG = new TreeNode<String>(0, "G");
		TreeNode<String> nodeH = new TreeNode<String>(0, "H");
		
		root = nodeA;
		
		root.leftChild = nodeB;
		root.rightChild = nodeC;
		
		nodeB.leftChild = nodeD;
		nodeB.rightChild = nodeE;
		
		nodeD.leftChild = nodeF;
		
		nodeC.leftChild = nodeG;
		nodeC.rightChild = nodeH;
		
	}
	
	/**
	 * ��ȡ�����/�߶�.
	 * @param root ���ڵ�.
	 * @return
	 */
	public static int getTreeHeight(TreeNode<String> root) {
		
		if (root == null) {
			return 0;
		}else {
			int leftH = getTreeHeight(root.leftChild);
			int rightH = getTreeHeight(root.rightChild);
			
			return Math.max(leftH + 1, rightH + 1);
		}
	}
	/**
	 * ��ȡ�ڵ����.
	 * @param root
	 * @return
	 */
	public static int getTreeSize(TreeNode<String> root) {
		if (root == null) {
			return 0;
		}else {
			// ��ǰ�ڵ㲻Ϊ����ڵ��������� 1.
			return getTreeSize(root.leftChild) + getTreeSize(root.rightChild) + 1;
		}
	}
	
	/**
	 * �ȸ�����, �� -> �� -> ��; ��������.
	 * @param root
	 */
	public static void preOrder(TreeNode<String> root) {
		if (root == null) {
			return ;
		}else {
			// 1. ��
			System.out.print(root.data);
			System.out.print(" ");
			// 2. ��
			preOrder(root.leftChild);
			// 3. ��
			preOrder(root.rightChild);
		}
	}
	
	/**
	 * �и����� , �� �� �� ; ������
	 * @param root
	 */
	private static void midOrder(TreeNode<String> root) {
		if (root == null) {
			return;
		}else {
			// 1. ��
			midOrder(root.leftChild);
			// 2. ��
			System.out.print(root.data);
			System.out.print(" ");
			// 3. right
			midOrder(root.rightChild);
		}
	}
	/**
	 * �������, �� �� �� ; ����
	 * @param root
	 */
	private static void afterOrder(TreeNode<String> root) {
		if (root == null) {
			return ;
		}else {
			// 1. ��
			afterOrder(root.leftChild);
			// 2. right
			afterOrder(root.rightChild);
			// 3. mid
			System.out.print(root.data);
			System.out.print(" ");
		}
	}
	
	
	/**
	 * �ȸ�����, mid left right ; �ǵ�����. ʹ��ջ.
	 * @param root
	 */
	public static void preOrderNotIteration(TreeNode<String> root) {
		if (root == null) {
			return;
		}
		
		Stack<TreeNode<String>> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			// 1. mid
			TreeNode<String> midNode = stack.pop();
			System.out.print(midNode.data);
			System.out.print(" ");
			
			// 2. �ҽڵ���ջ, ����ջ�� �Ƚ���� ���Ժ� ��ڵ���ջ,�����ȳ�.
			if (midNode.rightChild != null) {
				stack.push(midNode.rightChild);
			}
			if (midNode.leftChild != null) {
				stack.push(midNode.leftChild);
			}
		}
	}
	// F D B E A G C H 
	// F D B E A G C H 
	/**
	 * �������, left mid right ; �ǵݹ�.
	 * @param root
	 */
	public static void midOrderNotIteration(TreeNode<String> root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode<String>> stack = new Stack<>();
		TreeNode<String> node = root;
		
		while(node != null || !stack.isEmpty()){
			// 1. ������Ӳ�Ϊ���򽫵�ǰ�ڵ���ջ.
			while(node.leftChild != null){
				stack.push(node);
				node = node.leftChild;
			}
			// 2. ����node ������Ϊ�����node
			System.out.print(node.data);
			System.out.print(" ");
			node = node.rightChild;
			// 3. ��node ���Һ�������Ϊ��ǰԪ��.
			while(node == null){
				// 4. �Һ���Ϊ��.��ջ��.
				if (stack.isEmpty()) {
					break;
				}
				node = stack.pop();
				System.out.print(node.data);
				System.out.print(" ");
				node = node.rightChild;	
			}
		}
	}
	
//	/**
//	 * ��������, �ǵݹ�
//	 */
//	public static void afterOrderNotIteration(TreeNode<String> root) {
//		if (root == null) {
//			return;
//		}
//		
//		Stack<TreeNode<String>> stack = new Stack<>();
//		TreeNode<String> node = root;
//		
//		while(node != null || !stack.isEmpty()){
//			
//			while(node.leftChild != null){
//				stack.push(node);
//				node = node.leftChild;
//			}
//			
//			stack.push(node);
//			node = node.rightChild;
//			while(node == null){
//				// ��ջ
//				node = stack.pop();
//				if (node == null) {
//					return;
//				}
//				System.out.println(node.data);
//				node = stack.pop();
//				if (node == null) {
//					return;
//				}
//				
//			
//			}
//			stack.push(node);
//			node = node.rightChild;
//		}
//	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		makeTree();
		System.out.println("����� : " + getTreeHeight(root));
		System.out.println("�ڵ��� : " + getTreeSize(root));
		
		System.out.print("������� :��");
		preOrder(root);
		System.out.println();
		
		System.out.print("������� : ");
		midOrder(root);
		System.out.println();
		
		System.out.print("������� : ");
		afterOrder(root);
		System.out.println();
		
		System.out.print("�������(�ǵݹ�) : ");
		preOrderNotIteration(root);
		System.out.println();
		
		System.out.print("�������(�ǵݹ�) : ");
		midOrderNotIteration(root);
		System.out.println();
		
	}

	
	

	

}
